package com.example.boardgamespringbootminiproject.controller;

import com.example.boardgamespringbootminiproject.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is the controller for Category and Boardgame class.
 */
@RestController
@RequestMapping("/api") //http://localhost:9094/api
public class CategoryController {

private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
