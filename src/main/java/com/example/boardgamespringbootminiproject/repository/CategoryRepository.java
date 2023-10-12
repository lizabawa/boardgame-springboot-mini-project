package com.example.boardgamespringbootminiproject.repository;

import com.example.boardgamespringbootminiproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The repository interface for managing Category entities in the database.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Finds and retrieves a list of categories associated with a specific user.
     *
     * @param userId The ID of the user for whom to retrieve categories.
     * @return A list of categories associated with the specified user.
     */
    List<Category> findByUserId(Long userId);

    /**
     * Finds a category by its ID and the user's ID.
     *
     * @param categoryId The ID of the category to retrieve.
     * @param userId The ID of the user to whom the category belongs.
     * @return The category with the specified ID and associated with the specified user.
     */
    Category findByIdAndUserId(Long categoryId, Long userId);

    /**
     * Finds a category by its name and the user's ID.
     *
     * @param categoryName The name of the category to retrieve.
     * @param userId The ID of the user to whom the category belongs.
     * @return The category with the specified name and associated with the specified user.
     */
    Category findByNameAndUserId(String categoryName, Long userId);

    /**
     * Deletes a category by its ID.
     *
     * @param categoryId The ID of the category to delete.
     */
    @Transactional
    void deleteById(Long categoryId);
}
