package com.example.boardgamespringbootminiproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "boardgames")
public class Boardgame {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer players;

    @Column
    private Integer ageRange;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Boardgame() {
    }

    /**
     * This class is used to set and get any information pertaining to a boardgame.
     * @param id This is the boardgame id.
     * @param name This is the boardgame name.
     * @param players This is the number of players the boardgame can have.
     * @param ageRange This is the age range that the boardgame is meant for.
     */
    public Boardgame(Long id, String name, Integer players, Integer ageRange, Category category) {
        this.id = id;
        this.name = name;
        this.players = players;
        this.ageRange = ageRange;
        this.category = category;
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

    public Integer getPlayers() {
        return players;
    }

    public void setPlayers(Integer players) {
        this.players = players;
    }

    public Integer getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(Integer ageRange) {
        this.ageRange = ageRange;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Boardgame{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", players=" + players +
                ", ageRange=" + ageRange +
                ", category=" + category +
                '}';
    }
}
