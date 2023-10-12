package com.example.boardgamespringbootminiproject.model.response;

/**
 * This class has the getter and setter for the JSON Web token that we will use in other classes for user authentication.
 */
public class LoginResponse {
    private String jwt;

    /**
     * Constructs a new LoginResponse with the specified JWT token.
     *
     * @param jwt The JWT token to be included in the response.
     */
    public LoginResponse(String jwt) {
        this.jwt = jwt;
    }

    /**
     * Get the JWT token included in the response.
     *
     * @return The JWT token.
     */
    public String getJwt() {
        return jwt;
    }

    /**
     * Set the JWT token to be included in the response.
     *
     * @param jwt The JWT token to set in the response.
     */
    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
