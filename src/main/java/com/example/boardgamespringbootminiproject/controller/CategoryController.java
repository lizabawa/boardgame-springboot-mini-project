package com.example.boardgamespringbootminiproject.controller;

import com.example.boardgamespringbootminiproject.model.Boardgame;
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

    //CREATE A CATEGORY BOARDGAME ITEM
    @PostMapping(path = "/categories/{categoryId}/boardgames/") //http://localhost:9094/api/categories/1/boardgames/
    public Boardgame createCategoryBoardgame(@PathVariable(value = "categoryId") Long categoryId, @RequestBody Boardgame boardgameObject){
        return categoryService.createCategoryBoardgame(categoryId, boardgameObject);
    }

    //GET ALL CATEGORIES
    @GetMapping(path = "/categories/")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    //GET ALL BOARDGAMES WITHIN A CATEGORY
    @GetMapping(path = "/categories/{categoryId}/boardgames/") //http://localhost:9094/api/categories/1/boardgames/
    public List<Boardgame> getCategoryBoardgames(@PathVariable(value = "categoryId") Long categoryId){
        return categoryService.getCategoryBoardgames(categoryId);
    }

    //GET A CATEGORY
    @GetMapping(path = "/categories/{categoryId}")
    public Optional<Category> getCategory(@PathVariable(value = "categoryId") Long categoryId){
        return categoryService.getCategory(categoryId);
    }

    //GET A BOARDGAME FROM A CATEGORY
    @GetMapping(path = "/categories/{categoryId}/boardgames/{boardgameId}/") //http://localhost:9094/api/categories/1/boardgames/1/
    public Optional<Boardgame> getCategoryBoardgame(@PathVariable(value = "categoryId") Long categoryId, @PathVariable(value = "boardgameId") Long boardgameId){
        return categoryService.getCategoryBoardgame(categoryId, boardgameId);
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
