package com.example.boardgamespringbootminiproject.repository;

import com.example.boardgamespringbootminiproject.model.Boardgame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardgameRepository extends JpaRepository<Boardgame, Long> {
    Boardgame findByNameAndUserId(String boardgameName, Long userId);

    List<Boardgame> findAllByCategoryId(Long categoryId);

    Boardgame findByIdAndUserId(Long boardgameId, Long userId);
}
