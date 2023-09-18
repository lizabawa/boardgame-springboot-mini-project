package com.example.boardgamespringbootminiproject.model;

public class User {
    private Long id;
    private String userName;
    private String name;
    private String emailAddress;

    public User() {
    }

    public User(Long id, String userName, String name, String emailAddress) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.emailAddress = emailAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
