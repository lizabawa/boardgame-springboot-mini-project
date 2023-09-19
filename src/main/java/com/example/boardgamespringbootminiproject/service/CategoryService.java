package com.example.boardgamespringbootminiproject.service;

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

    public User getCurrentlyLoggedInUser(){
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //once user is authorized via JWT, grabs the users login details
        return userDetails.getUser();
    }
}
