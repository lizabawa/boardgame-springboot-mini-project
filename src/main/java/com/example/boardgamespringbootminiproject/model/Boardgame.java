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
    private Integer maxPlayers;

    @Column
    private Integer time;

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
     * @param maxPlayers This is the number of players the boardgame can have.
     * @param time This is the age range that the boardgame is meant for.
     */
    public Boardgame(Long id, String name, Integer maxPlayers, Integer time, Category category) {
        this.id = id;
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.time = time;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer players) {
        this.maxPlayers = players;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer ageRange) {
        this.time = ageRange;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Boardgame{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", players=" + maxPlayers +
                ", ageRange=" + time +
                ", category=" + category +
                '}';
    }
}
