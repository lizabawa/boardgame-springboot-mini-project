package com.example.boardgamespringbootminiproject.model;

public class Category {
    private Long id;
    private String name;

    public Category() {
    }

    /**
     * This class is used to get and set any information pertaining to a category.
     * @param id This is the category id.
     * @param name This is the category name.
     */
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
