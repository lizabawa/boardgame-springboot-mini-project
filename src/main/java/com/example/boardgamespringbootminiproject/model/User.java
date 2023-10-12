package com.example.boardgamespringbootminiproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * An entity class representing a user in the database.
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * The unique identifier for the user.
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The username of the user.
     */
    @Column
    private String userName;

    /**
     * The email address of the user (unique).
     */
    @Column(unique = true) //emailAddresses are unique
    private String emailAddress;

    /**
     * The password associated with the user.
     */
    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * A list of categories associated with this user.
     */
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user")
    private List<Category> categoryList;

    /**
     * A list of board games associated with this user.
     */
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user")
    private List<Boardgame> boardgameList;

    public User() {
    }

    /**
     * This class is used to set and get any information pertaining to a user.
     * @param id This is the User id
     * @param userName This is the User name
     * @param emailAddress This is the User email address
     * @param password This is the User password
     */
    public User(Long id, String userName, String emailAddress, String password) {
        this.id = id;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    /**
     * Get the unique identifier for the user.
     *
     * @return The user's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the username of the user.
     *
     * @param userName The username to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get the email address of the user.
     *
     * @return The user's email address.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Set the email address of the user.
     *
     * @param emailAddress The email address to set.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Get the password of the user.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password of the user.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
