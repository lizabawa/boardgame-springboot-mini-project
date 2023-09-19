package com.example.boardgamespringbootminiproject.controller;

import com.example.boardgamespringbootminiproject.model.Category;
import com.example.boardgamespringbootminiproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * This class is the controller for Category and Boardgame class.
 */
@RestController
@RequestMapping("/api") //http://localhost:9094/api
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //CREATE A NEW CATEGORY
    @PostMapping(path = "/categories/") //http://localhost:9094/api/categories/
    public Category createCategory(Category categoryObject){
        return categoryService.createCategory(categoryObject);
    }


}
