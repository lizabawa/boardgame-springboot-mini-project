package com.example.boardgamespringbootminiproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * An entity class representing a category in the database.
 */
@Entity
@Table(name = "categories")
public class Category {

    /**
     * The unique identifier for the category.
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the category.
     */
    @Column
    private String name;

    /**
     * A list of board games associated with this category.
     */
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE) //when category instance is searched, fetch all assoc boardgames
    private List<Boardgame> boardgameList;

    /**
     * The user who owns or created the category.
     */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

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

    /**
     * Get the name of the category.
     *
     * @return The name of the category.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the category.
     *
     * @param name The name of the category to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the user associated with the category.
     *
     * @return The user associated with the category.
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the user associated with the category.
     *
     * @param user The user to associate with the category.
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", boardgameList=" + boardgameList +
                '}';
    }
}
