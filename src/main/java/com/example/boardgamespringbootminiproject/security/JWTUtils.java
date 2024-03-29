package com.example.boardgamespringbootminiproject.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A utility class for handling JWT (JSON Web Token) operations.
 */
@Service
public class JWTUtils {
    Logger logger = Logger.getLogger(JWTUtils.class.getName());

    @Value("${jwt-secret}")
    private String jwtSecret;

    @Value("${jwt-expiration-ms}")
    private int jwtExpirationMS;

    /**
     * Generates a JWT token for a user after they log in.
     *
     * @param myUserDetails The user details for whom the token is generated.
     * @return The generated JWT token.
     */
    public String generateJwtToken(MyUserDetails myUserDetails){
        return Jwts.builder()
                .setSubject((myUserDetails.getUsername())) //get just the email address
                .setIssuedAt(new Date()) //set time to whatever the current time is
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMS)) //sets expiration date to current time + the jwt expiration date
                .signWith(SignatureAlgorithm.HS256, jwtSecret) //HS256 is the algorithm
                .compact(); //compacts everything together
    }

    /**
     * Extracts the user's email address from a JWT token.
     *
     * @param token The JWT token from which to extract the email address.
     * @return The user's email address.
     */
    public String getUserNameFromJwtToken(String token){
        return Jwts.parserBuilder().setSigningKey(jwtSecret).build()
                .parseClaimsJws(token) //claims are when token was issued, so this grabs the token, the entire payload
                .getBody().getSubject(); //getting the user email address
    }

    /**
     * Validates the integrity of a JWT token.
     *
     * @param authToken The JWT token to validate.
     * @return `true` if the token is valid, `false` otherwise.
     */
    public boolean validateJwtToken(String authToken){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken); //there is also a parseClaimJwt method
            return true;
        } catch (SecurityException e){
            logger.log(Level.SEVERE, "Invalid JWT signature {}" + e.getMessage());
        } catch (MalformedJwtException e){
            logger.log(Level.SEVERE, "Invalid JWT Malformed JWT Exception {}" + e.getMessage());
        } catch (ExpiredJwtException e){
            logger.log(Level.SEVERE, "Expired JWT Exception {}" + e.getMessage());
        }catch (UnsupportedJwtException e) {
            logger.log(Level.SEVERE, "JWT authToken is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
