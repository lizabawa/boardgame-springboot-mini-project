package com.example.boardgamespringbootminiproject.service;

import com.example.boardgamespringbootminiproject.repository.BoardgameRepository;
import com.example.boardgamespringbootminiproject.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    private BoardgameRepository boardgameRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setBoardgameRepository(BoardgameRepository boardgameRepository) {
        this.boardgameRepository = boardgameRepository;
    }
}
