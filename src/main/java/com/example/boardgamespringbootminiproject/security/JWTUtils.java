package com.example.boardgamespringbootminiproject.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class correlates to user actions dealing with log in. It generates a JWT token upon every successful login and validates token upon CRUD requests
 */
@Service
public class JWTUtils {
    Logger logger = Logger.getLogger(JWTUtils.class.getName());

    @Value("${jwt-secret}")
    private String jwtSecret;

    @Value("${jwt-expiration-ms}")
    private int jwtExpirationMS;

    //GENERATES A TOKEN ONCE USER LOGS IN
    public String generateJwtToken(MyUserDetails myUserDetails){
        return Jwts.builder()
                .setSubject((myUserDetails.getUsername())) //get just the email address
                .setIssuedAt(new Date()) //set time to whatever the current time is
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMS)) //sets expiration date to current time + the jwt expiration date
                .signWith(SignatureAlgorithm.HS256, jwtSecret) //HS256 is the algorithm
                .compact(); //compacts everything together
    }

    //FOR EVERY SINGLE REQUEST
    public String getUserNameFromJwtToken(String token){
        return Jwts.parserBuilder().setSigningKey(jwtSecret).build()
                .parseClaimsJws(token) //claims are when token was issued, so this grabs the token, the entire payload
                .getBody().getSubject(); //getting the user email address
    }

    //FOR EVERY SINGLE REQUEST
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
