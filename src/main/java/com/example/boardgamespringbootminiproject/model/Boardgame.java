package com.example.boardgamespringbootminiproject.model;

public class Boardgame {
    private Long id;
    private String name;
    private Integer players;
    private Integer ageRange;

    public Boardgame() {
    }

    /**
     * This class is used to set and get any information pertaining to a boardgame.
     * @param id This is the boardgame id.
     * @param name This is the boardgame name.
     * @param players This is the number of players the boardgame can have.
     * @param ageRange This is the age range that the boardgame is meant for.
     */
    public Boardgame(Long id, String name, Integer players, Integer ageRange) {
        this.id = id;
        this.name = name;
        this.players = players;
        this.ageRange = ageRange;
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

    @Override
    public String toString() {
        return "Boardgame{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", players=" + players +
                ", ageRange=" + ageRange +
                '}';
    }
}
