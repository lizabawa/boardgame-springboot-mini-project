package com.example.boardgamespringbootminiproject.repository;

import com.example.boardgamespringbootminiproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByUserId(Long userId);

    //find by categoryId AND userId
    Category findByIdAndUserId(Long categoryId, Long userId);

    Category findByNameAndUserId(String categoryName, Long userId);

    @Transactional
    void deleteById(Long categoryId);
}
