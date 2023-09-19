package com.example.boardgamespringbootminiproject.repository;

import com.example.boardgamespringbootminiproject.model.Boardgame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardgameRepository extends JpaRepository<Boardgame, Long> {
}
