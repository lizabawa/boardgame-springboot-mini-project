package com.example.boardgamespringbootminiproject.model.request;

/**
 * This class has the getters for Login Request emailaddress and password. Setters are not necessary in this class because it is meant for REQUESTING login information.
 */
public class LoginRequest {
    private String emailAddress;
    private String password;

    /**
     * Get the email address of the user.
     *
     * @return The user's email address.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Get the password of the user.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }
}
