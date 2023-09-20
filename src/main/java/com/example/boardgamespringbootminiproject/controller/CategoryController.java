package com.example.boardgamespringbootminiproject.controller;

import com.example.boardgamespringbootminiproject.model.Category;
import com.example.boardgamespringbootminiproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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
    public Category createCategory(@RequestBody Category categoryObject){
        return categoryService.createCategory(categoryObject);
    }

    //GET ALL CATEGORIES
    @GetMapping(path = "/categories/")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    //GET A CATEGORY
    @GetMapping(path = "/categories/{categoryId}")
    public Optional<Category> getCategory(@PathVariable(value = "categoryId") Long categoryId){
        return categoryService.getCategory(categoryId);
    }

    //UPDATE A CATEGORY
    @PutMapping(path = "/categories/{categoryId}/") //http://localhost:9094/api/categories/1/
    public Optional<Category> updateCategory(@PathVariable(value = "categoryId") Long categoryId, @RequestBody Category categoryObject){
        return categoryService.updateCategory(categoryId, categoryObject);
    }

    //DELETE CATEGORY
    @DeleteMapping(path = "categories/{categoryId}/")
    public Optional<Category> deleteCategory(@PathVariable(value = "categoryId") Long categoryId){
        return categoryService.deleteCategory(categoryId);
    }
}
