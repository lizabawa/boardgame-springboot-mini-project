package com.example.boardgamespringbootminiproject.service;

import com.example.boardgamespringbootminiproject.exception.InformationAlreadyExistsException;
import com.example.boardgamespringbootminiproject.exception.InformationNotFoundException;
import com.example.boardgamespringbootminiproject.model.Category;
import com.example.boardgamespringbootminiproject.model.User;
import com.example.boardgamespringbootminiproject.repository.BoardgameRepository;
import com.example.boardgamespringbootminiproject.repository.CategoryRepository;
import com.example.boardgamespringbootminiproject.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    private BoardgameRepository boardgameRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

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
        Category category = categoryRepository.findByName(categoryObject.getName());
        if (category == null){
            categoryObject.setUser(getCurrentlyLoggedInUser()); //assign this category to the currently logged in user
            return categoryRepository.save(categoryObject);
        } else {
            throw new InformationAlreadyExistsException("Category " + categoryObject.getName() + " already exists.");
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

    //GET A CATEGORY
    public Optional<Category> getCategory(Long categoryId){
        Optional<Category> categoryOptional = Optional.ofNullable(categoryRepository.findByIdAndUserId(categoryId, getCurrentlyLoggedInUser().getId()));

        if (categoryOptional.isEmpty()){
            throw new InformationNotFoundException("Category with an id " + categoryId + " not found.");
        } else {
            return categoryOptional;
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
}
