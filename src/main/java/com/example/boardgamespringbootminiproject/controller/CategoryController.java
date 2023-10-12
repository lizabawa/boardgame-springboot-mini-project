package com.example.boardgamespringbootminiproject.controller;

import com.example.boardgamespringbootminiproject.model.Boardgame;
import com.example.boardgamespringbootminiproject.model.Category;
import com.example.boardgamespringbootminiproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 * This class is the controller for managing the Category and Boardgame class.
 */
@RestController
@RequestMapping("/api") //http://localhost:9094/api
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Creates a new category.
     *
     * @param categoryObject The category object to create.
     * @return The created category.
     */
    @PostMapping(path = "/categories/") //http://localhost:9094/api/categories/
    public Category createCategory(@RequestBody Category categoryObject){
        return categoryService.createCategory(categoryObject);
    }

    /**
     * Creates a category board game item.
     *
     * @param categoryId The ID of the category to associate the board game with.
     * @param boardgameObject The board game object to create.
     * @return The created board game.
     */
    @PostMapping(path = "/categories/{categoryId}/boardgames/") //http://localhost:9094/api/categories/1/boardgames/
    public Boardgame createCategoryBoardgame(@PathVariable(value = "categoryId") Long categoryId, @RequestBody Boardgame boardgameObject){
        return categoryService.createCategoryBoardgame(categoryId, boardgameObject);
    }

    /**
     * Gets all categories.
     *
     * @return A list of all categories.
     */
    @GetMapping(path = "/categories/")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    /**
     * Gets all board games within a category.
     *
     * @param categoryId The ID of the category to retrieve board games from.
     * @return A list of board games in the category.
     */
    @GetMapping(path = "/categories/{categoryId}/boardgames/") //http://localhost:9094/api/categories/1/boardgames/
    public List<Boardgame> getCategoryBoardgames(@PathVariable(value = "categoryId") Long categoryId){
        return categoryService.getCategoryBoardgames(categoryId);
    }

    /**
     * Gets a category by its ID.
     *
     * @param categoryId The ID of the category to retrieve.
     * @return An optional containing the category, or empty if not found.
     */
    @GetMapping(path = "/categories/{categoryId}")
    public Optional<Category> getCategory(@PathVariable(value = "categoryId") Long categoryId){
        return categoryService.getCategory(categoryId);
    }

    /**
     * Gets a board game from a category by their IDs.
     *
     * @param categoryId The ID of the category.
     * @param boardgameId The ID of the board game.
     * @return An optional containing the board game, or empty if not found.
     */
    @GetMapping(path = "/categories/{categoryId}/boardgames/{boardgameId}/") //http://localhost:9094/api/categories/1/boardgames/1/
    public Optional<Boardgame> getCategoryBoardgame(@PathVariable(value = "categoryId") Long categoryId, @PathVariable(value = "boardgameId") Long boardgameId){
        return categoryService.getCategoryBoardgame(categoryId, boardgameId);
    }

    /**
     * Updates a category by its ID.
     *
     * @param categoryId The ID of the category to update.
     * @param categoryObject The updated category object.
     * @return An optional containing the updated category, or empty if not found.
     */
    @PutMapping(path = "/categories/{categoryId}/") //http://localhost:9094/api/categories/1/
    public Optional<Category> updateCategory(@PathVariable(value = "categoryId") Long categoryId, @RequestBody Category categoryObject){
        return categoryService.updateCategory(categoryId, categoryObject);
    }

    /**
     * Updates a category board game item by their IDs.
     *
     * @param categoryId The ID of the category.
     * @param boardgameId The ID of the board game to update.
     * @param boardgameObject The updated board game object.
     * @return An optional containing the updated board game, or empty if not found.
     */
    @PutMapping(path = "/categories/{categoryId}/boardgames/{boardgameId}")
    public Optional<Boardgame> updateCategoryBoardgame(@PathVariable(value = "categoryId") Long categoryId, @PathVariable(value = "boardgameId") Long boardgameId, @RequestBody Boardgame boardgameObject){
        return categoryService.updateCategoryBoardgame(categoryId, boardgameId, boardgameObject);
    }

    /**
     * Deletes a category by its ID.
     *
     * @param categoryId The ID of the category to delete.
     * @return An optional containing the deleted category, or empty if not found.
     */
    @DeleteMapping(path = "categories/{categoryId}/")
    public Optional<Category> deleteCategory(@PathVariable(value = "categoryId") Long categoryId){
        return categoryService.deleteCategory(categoryId);
    }

    /**
     * Deletes a category board game item by their IDs.
     *
     * @param categoryId The ID of the category.
     * @param boardgameId The ID of the board game to delete.
     * @return An optional containing the deleted board game, or empty if not found.
     */
    @DeleteMapping(path = "categories/{categoryId}/boardgames/{boardgameId}")
    public Optional<Boardgame> deleteCategoryBoardgame(@PathVariable(value = "categoryId") Long categoryId, @PathVariable(value = "boardgameId") Long boardgameId){
        return categoryService.deleteCategoryBoardgame(categoryId, boardgameId);
    }
}
