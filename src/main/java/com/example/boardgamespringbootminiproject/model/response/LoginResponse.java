package com.example.boardgamespringbootminiproject.model.response;

/**
 * This class has the getter and setter for the JSON Web token that we will use in other classes for user authentication.
 */
public class LoginResponse {
    private String jwt;

    /**
     *
     * @param jwt This is the JSON Web token.
     */
    public LoginResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
