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
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id")
    private Category category;

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

    /**
     * Get the name of the board game.
     *
     * @return The name of the board game.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the board game.
     *
     * @param name The name of the board game to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the maximum number of players for the board game.
     *
     * @return The maximum number of players.
     */
    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    /**
     * Set the maximum number of players for the board game.
     *
     * @param players The maximum number of players to set.
     */
    public void setMaxPlayers(Integer players) {
        this.maxPlayers = players;
    }

    /**
     * Get the playing time of the board game.
     *
     * @return The playing time of the board game.
     */
    public Integer getTime() {
        return time;
    }

    /**
     * Set the playing time of the board game.
     *
     * @param time The playing time to set.
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     * Get the user associated with the board game.
     *
     * @return The user associated with the board game.
     */
    public User getUser() {
        return user;
    }


    /**
     * Set the user associated with the board game.
     *
     * @param user The user to associate with the board game.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Set the category to which the board game belongs.
     *
     * @param category The category to associate with the board game.
     */
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
