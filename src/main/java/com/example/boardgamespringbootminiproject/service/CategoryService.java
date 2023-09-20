package com.example.boardgamespringbootminiproject.service;

import com.example.boardgamespringbootminiproject.exception.InformationAlreadyExistsException;
import com.example.boardgamespringbootminiproject.exception.InformationNotFoundException;
import com.example.boardgamespringbootminiproject.model.Boardgame;
import com.example.boardgamespringbootminiproject.model.Category;
import com.example.boardgamespringbootminiproject.model.User;
import com.example.boardgamespringbootminiproject.repository.BoardgameRepository;
import com.example.boardgamespringbootminiproject.repository.CategoryRepository;
import com.example.boardgamespringbootminiproject.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class contains the business logic for Category and Boardgame CRUD methods.
 */
@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    private BoardgameRepository boardgameRepository;

    /**
     *
     * @param categoryRepository This is the repository that contains all categories.
     */
    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     *
     * @param boardgameRepository This is the repository that contains all boardgames.
     */
    @Autowired
    public void setBoardgameRepository(BoardgameRepository boardgameRepository) {
        this.boardgameRepository = boardgameRepository;
    }

    //GET CURRENT LOGGED IN USER
    public User getCurrentlyLoggedInUser(){
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //once user is authorized via JWT, grabs the users login details
        return userDetails.getUser();
    }

    //CREATE A NEW CATEGORY
    public Category createCategory(Category categoryObject){
        Category category = categoryRepository.findByNameAndUserId(categoryObject.getName(), getCurrentlyLoggedInUser().getId());
        if (category == null){
            categoryObject.setUser(getCurrentlyLoggedInUser()); //assign this category to the currently logged in user
            return categoryRepository.save(categoryObject);
        } else {
            throw new InformationAlreadyExistsException("Category " + categoryObject.getName() + " already exists.");
        }
    }

    //CREATE A CATEGORY BOARDGAME ITEM
    public Boardgame createCategoryBoardgame(Long categoryId, Boardgame boardgameObject){
        Optional<Category> categoryOptional = Optional.ofNullable(categoryRepository.findByIdAndUserId(categoryId, getCurrentlyLoggedInUser().getId()));
        Optional<Boardgame> boardgameOptional = Optional.ofNullable(boardgameRepository.findByNameAndUserId(boardgameObject.getName(), getCurrentlyLoggedInUser().getId()));
        if (categoryOptional != null){
            if (boardgameOptional.isEmpty()){
            boardgameObject.setCategory(categoryOptional.get());
            return boardgameRepository.save(boardgameObject);
            }else {
                throw new InformationAlreadyExistsException("Boardgame with name of " + boardgameObject.getName() + " already exists.");
            }
        } else {
            throw new InformationNotFoundException("Category with id " + categoryId + " not found.");
        }
    }

    //GET ALL CATEGORIES
    public List<Category> getCategories(){
        List<Category> categoryList = categoryRepository.findByUserId(getCurrentlyLoggedInUser().getId());
        if (categoryList.isEmpty()){
            throw new InformationNotFoundException("No categories found for user with id " + getCurrentlyLoggedInUser().getId());
        } else {
            return categoryList;
        }
    }

    //GET ALL BOARDGAMES WITHIN A CATEGORY
    public List<Boardgame> getCategoryBoardgames(Long categoryId){
        List<Category> categoryOptional = categoryRepository.findByUserId(getCurrentlyLoggedInUser().getId());
        if (categoryOptional != null){
            return boardgameRepository.findAllByCategoryId(categoryId);
        } else {
            throw new InformationNotFoundException("No boardgames found for category with id " + categoryId);
        }
    }

    //GET A CATEGORY
    public Optional<Category> getCategory(Long categoryId){
        Optional<Category> categoryOptional = Optional.ofNullable(categoryRepository.findByIdAndUserId(categoryId, getCurrentlyLoggedInUser().getId()));
        if (categoryOptional.isEmpty()){
            throw new InformationNotFoundException("Category with an id " + categoryId + " not found.");
        } else {
            return categoryOptional;
        }
    }

    //GET A BOARDGAME FROM A CATEGORY
    public Optional<Boardgame> getCategoryBoardgame(Long categoryId, Long boardgameId){
        Optional<Boardgame> boardgameOptional = Optional.ofNullable(boardgameRepository.findByIdAndUserId(boardgameId, getCurrentlyLoggedInUser().getId()));
        Optional<Category> categoryOptional = Optional.ofNullable(categoryRepository.findByIdAndUserId(categoryId, getCurrentlyLoggedInUser().getId()));
        if (categoryOptional.isPresent()) {
            if (boardgameOptional.isPresent()) {
                return boardgameOptional;
            } else {
                throw new InformationNotFoundException("Item " + boardgameId + " not found");
            }
        }else {
            throw new InformationNotFoundException("category " + categoryId + " not found");
        }
    }

    //UPDATE A CATEGORY
    public Optional<Category> updateCategory(Long categoryId, Category categoryObject){
        Optional<Category> categoryOptional = Optional.ofNullable(categoryRepository.findByIdAndUserId(categoryId, getCurrentlyLoggedInUser().getId()));
        if (categoryOptional.isEmpty()){
            throw new InformationNotFoundException("Category with id of " + categoryId + " not found.");
        } else if (categoryOptional.equals(categoryObject)) {
            throw new InformationAlreadyExistsException("Category with id of " + categoryId + " is already up to date");
        } else {
            Category updateCategory = categoryRepository.findById(categoryId).get();
            updateCategory.setName(categoryObject.getName());
            return Optional.of(categoryRepository.save(updateCategory));
        }
    }

    //UPDATE CATEGORY BOARDGAME
    public Optional<Boardgame> updateCategoryBoardgame(Long categoryId, Long boardgameId, Boardgame boardgameObject){
        Optional<Category> categoryOptional = Optional.ofNullable(categoryRepository.findByIdAndUserId(categoryId, getCurrentlyLoggedInUser().getId()));
        Optional<Boardgame> itemOptional = Optional.ofNullable(boardgameRepository.findByIdAndUserId(boardgameId, getCurrentlyLoggedInUser().getId()));
        if(categoryOptional.isPresent()) {
            if(itemOptional.isPresent()){
                if(boardgameObject.equals(itemOptional.get())){
                    throw new InformationAlreadyExistsException("Boardgame has already been updated");
                }else {
                    Boardgame updateBoardgame = boardgameRepository.findById(boardgameId).get();
                    updateBoardgame.setName(boardgameObject.getName());
                    updateBoardgame.setMaxPlayers(boardgameObject.getMaxPlayers());
                    updateBoardgame.setTime(boardgameObject.getTime());
                    return Optional.of(boardgameRepository.save(updateBoardgame));
                }
            } else {
                throw new InformationNotFoundException("Cannot find an item of id " + boardgameId);
            }
        } else {
            throw new InformationNotFoundException("Cannot find a category of id " + categoryId);
        }
    }

    //DELETE CATEGORY
    public Optional<Category> deleteCategory(Long categoryId){
        Optional<Category> categoryOptional = Optional.ofNullable(categoryRepository.findByIdAndUserId(categoryId, getCurrentlyLoggedInUser().getId()));
        if (categoryOptional.isPresent()){
            categoryRepository.deleteCategoryById(categoryId);
            return categoryOptional;
        }else {
            throw new InformationNotFoundException("Category with id " + categoryId + " not found.");
        }
    }

    //DELETE CATEGORY BOARDGAME
    public Optional<Boardgame> deleteCategoryBoardgame(Long categoryId, Long boardgameId){
        Optional<Category> categoryOptional = Optional.ofNullable(categoryRepository.findByIdAndUserId(categoryId, getCurrentlyLoggedInUser().getId()));
        Optional<Boardgame> itemOptional = Optional.ofNullable(boardgameRepository.findByIdAndUserId(boardgameId, getCurrentlyLoggedInUser().getId()));
        if (categoryOptional.isPresent()) {
            if (itemOptional.isPresent()) {
                boardgameRepository.deleteById(boardgameId);
                return itemOptional;
            } else {
                throw new InformationNotFoundException("Unable to delete as id " + boardgameId + " does not exist");
            }
        }else {
            throw new InformationNotFoundException("Unable to delete as category " + categoryId + " does not exist");
        }
    }
}
