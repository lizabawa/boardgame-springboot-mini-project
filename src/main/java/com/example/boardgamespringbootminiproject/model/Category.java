package com.example.boardgamespringbootminiproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE) //when category instance is searched, fetch all assoc boardgames
    private List<Boardgame> boardgameList;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Boardgame> getBoardgameList() {
        return boardgameList;
    }

    public void setBoardgameList(List<Boardgame> boardgameList) {
        this.boardgameList = boardgameList;
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
