package com.example.boardgamespringbootminiproject.service;

import com.example.boardgamespringbootminiproject.exception.InformationAlreadyExistsException;
import com.example.boardgamespringbootminiproject.model.Category;
import com.example.boardgamespringbootminiproject.model.User;
import com.example.boardgamespringbootminiproject.repository.BoardgameRepository;
import com.example.boardgamespringbootminiproject.repository.CategoryRepository;
import com.example.boardgamespringbootminiproject.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
        if (category != null){
            categoryObject.setUser(getCurrentlyLoggedInUser()); //assign this category to the currently logged in user
            return categoryRepository.save(categoryObject);
        } else {
            throw new InformationAlreadyExistsException("Category " + categoryObject.getName() + " already exists.");
        }
    }
}
