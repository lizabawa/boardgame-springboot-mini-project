package com.example.boardgamespringbootminiproject.service;

import com.example.boardgamespringbootminiproject.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public String getHelloWorld(){
        return "Hello World";
    }
}
