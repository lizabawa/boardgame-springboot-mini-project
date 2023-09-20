package com.example.boardgamespringbootminiproject.model.request;

/**
 * This class has the getters for Login Request emailaddress and password. Setters are not necessary in this class because it is meant for REQUESTING login information.
 */
public class LoginRequest {
    private String emailAddress;
    private String password;

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }
}
