package com.example.boardgamespringbootminiproject.repository;

import com.example.boardgamespringbootminiproject.model.Boardgame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A repository interface for accessing and managing board game data in the database.
 */
@Repository
public interface BoardgameRepository extends JpaRepository<Boardgame, Long> {

    /**
     * Find a board game by name and user ID.
     *
     * @param boardgameName The name of the board game.
     * @param userId The ID of the user associated with the board game.
     * @return The board game with the specified name and user ID.
     */
    Boardgame findByNameAndUserId(String boardgameName, Long userId);

    /**
     * Find all board games within a specific category.
     *
     * @param categoryId The ID of the category.
     * @return A list of board games within the specified category.
     */
    List<Boardgame> findAllByCategoryId(Long categoryId);

    /**
     * Find a board game by ID and user ID.
     *
     * @param boardgameId The ID of the board game.
     * @param userId The ID of the user associated with the board game.
     * @return The board game with the specified ID and user ID.
     */
    Boardgame findByIdAndUserId(Long boardgameId, Long userId);
}
