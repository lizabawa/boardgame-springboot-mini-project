package com.example.boardgamespringbootminiproject.repository;

import com.example.boardgamespringbootminiproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String categoryName);

    List<Category> findByUserId(Long userId);
}
