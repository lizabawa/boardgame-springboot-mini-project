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

    /**
     * Gets the currently logged in user.
     *
     * @return The currently logged in user.
     */
    public User getCurrentlyLoggedInUser(){
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //once user is authorized via JWT, grabs the users login details
        return userDetails.getUser();
    }

    /**
     * Creates a new category.
     *
     * @param categoryObject The category object to create.
     * @return The created category.
     * @throws InformationAlreadyExistsException If a category with the same name already exists.
     */
    public Category createCategory(Category categoryObject){
        Category category = categoryRepository.findByNameAndUserId(categoryObject.getName(), getCurrentlyLoggedInUser().getId());
        if (category == null){
            categoryObject.setUser(getCurrentlyLoggedInUser()); //assign this category to the currently logged in user
            return categoryRepository.save(categoryObject);
        } else {
            throw new InformationAlreadyExistsException("Category " + categoryObject.getName() + " already exists.");
        }
    }

    /**
     * Creates a category board game item.
     *
     * @param categoryId The ID of the category to associate the board game with.
     * @param boardgameObject The board game object to create.
     * @return The created board game.
     * @throws InformationAlreadyExistsException If a board game with the same name already exists.
     * @throws InformationNotFoundException If the category with the given ID is not found.
     */
    public Boardgame createCategoryBoardgame(Long categoryId, Boardgame boardgameObject){
        Optional<Category> categoryOptional = Optional.ofNullable(categoryRepository.findByIdAndUserId(categoryId, getCurrentlyLoggedInUser().getId()));
        Optional<Boardgame> boardgameOptional = Optional.ofNullable(boardgameRepository.findByNameAndUserId(boardgameObject.getName(), getCurrentlyLoggedInUser().getId()));

        if (categoryOptional.isPresent()){
            if (boardgameOptional.isEmpty()){
                boardgameObject.setCategory(categoryOptional.get());
                boardgameObject.setUser(getCurrentlyLoggedInUser());
                return boardgameRepository.save(boardgameObject);
            } else {
                throw new InformationAlreadyExistsException("Boardgame with name " + boardgameObject.getName() + " already exists");
            }
        } else {
           throw new InformationNotFoundException("Category with id " + categoryId + " not found.");
        }
    }

    /**
     * Gets all categories associated with the currently logged in user.
     *
     * @return A list of categories.
     * @throws InformationNotFoundException If no categories are found for the user.
     */
    public List<Category> getCategories(){
        List<Category> categoryList = categoryRepository.findByUserId(getCurrentlyLoggedInUser().getId());
        if (categoryList.isEmpty()){
            throw new InformationNotFoundException("No categories found for user with id " + getCurrentlyLoggedInUser().getId());
        } else {
            return categoryList;
        }
    }

    /**
     * Gets all board games within a category.
     *
     * @param categoryId The ID of the category to retrieve board games from.
     * @return A list of board games in the category.
     * @throws InformationNotFoundException If no board games are found for the category.
     */
    public List<Boardgame> getCategoryBoardgames(Long categoryId){
        List<Category> categoryOptional = categoryRepository.findByUserId(getCurrentlyLoggedInUser().getId());
        if (categoryOptional != null){
            return boardgameRepository.findAllByCategoryId(categoryId);
        } else {
            throw new InformationNotFoundException("No boardgames found for category with id " + categoryId);
        }
    }

    /**
     * Gets a category by its ID.
     *
     * @param categoryId The ID of the category to retrieve.
     * @return An optional containing the category, or empty if not found.
     * @throws InformationNotFoundException If the category with the given ID is not found.
     */
    public Optional<Category> getCategory(Long categoryId){
        Optional<Category> categoryOptional = Optional.ofNullable(categoryRepository.findByIdAndUserId(categoryId, getCurrentlyLoggedInUser().getId()));
        if (categoryOptional.isEmpty()){
            throw new InformationNotFoundException("Category with an id " + categoryId + " not found.");
        } else {
            return categoryOptional;
        }
    }

    /**
     * Gets a board game from a category by its IDs.
     *
     * @param categoryId The ID of the category.
     * @param boardgameId The ID of the board game.
     * @return An optional containing the board game, or empty if not found.
     * @throws InformationNotFoundException If the category or board game with the given IDs is not found.
     */
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

    /**
     * Updates a category.
     *
     * @param categoryId The ID of the category to update.
     * @param categoryObject The updated category object.
     * @return An optional containing the updated category, or empty if not found.
     * @throws InformationNotFoundException If the category with the given ID is not found.
     * @throws InformationAlreadyExistsException If the updated category is the same as the existing one.
     */
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

    /**
     * Updates a category board game item.
     *
     * @param categoryId The ID of the category to which the board game belongs.
     * @param boardgameId The ID of the board game to update.
     * @param boardgameObject The updated board game object.
     * @return An optional containing the updated board game, or empty if not found.
     * @throws InformationNotFoundException If the category or board game with the given IDs is not found.
     * @throws InformationAlreadyExistsException If the updated board game is the same as the existing one.
     */
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

    /**
     * Deletes a category by its ID.
     *
     * @param categoryId The ID of the category to delete.
     * @return An optional containing the deleted category, or empty if not found.
     * @throws InformationNotFoundException If the category with the given ID is not found.
     */
    public Optional<Category> deleteCategory(Long categoryId){
        Optional<Category> categoryOptional = Optional.ofNullable(categoryRepository.findByIdAndUserId(categoryId, getCurrentlyLoggedInUser().getId()));
        if (categoryOptional.isPresent()){
            //TODO fix deleteCategory bug
            //DELETE ALL BOARDGAMES ASSOC WITH THE ID FIRST
            //THEN DELETE THE CATEGORY
            categoryRepository.deleteById(categoryId);
            return categoryOptional;
        }else {
            throw new InformationNotFoundException("Category with id " + categoryId + " not found.");
        }
    }

    /**
     * Deletes a category board game item by their IDs.
     *
     * @param categoryId The ID of the category.
     * @param boardgameId The ID of the board game to delete.
     * @return An optional containing the deleted board game, or empty if not found.
     * @throws InformationNotFoundException If the category or board game with the given IDs is not found.
     */
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
