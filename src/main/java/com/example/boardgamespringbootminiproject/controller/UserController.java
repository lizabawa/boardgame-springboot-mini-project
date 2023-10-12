package com.example.boardgamespringbootminiproject.controller;

import com.example.boardgamespringbootminiproject.model.User;
import com.example.boardgamespringbootminiproject.model.request.LoginRequest;
import com.example.boardgamespringbootminiproject.model.response.LoginResponse;
import com.example.boardgamespringbootminiproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * This class is the controller for the User class.
 */
@RestController
@RequestMapping(path = "/auth/users/") //http://localhost:9094/auth/users/
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registers a new user.
     *
     * @param userObject The user object to register.
     * @return The created user.
     */
    @PostMapping(path = "/register/") //http://localhost:9094/auth/users/register/
    public User createUser(@RequestBody User userObject){
        return userService.createUser(userObject);
    }

    /**
     * Logs in a user and provides an authentication token (JWT) upon successful authentication.
     *
     * @param loginRequest The login request object containing user credentials.
     * @return A ResponseEntity containing the authentication token (JWT) upon successful login,
     *         or an unauthorized response if authentication fails.
     */
    @PostMapping(path = "/login/") //http://localhost:9094/auth/users/login/
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        Optional<String> jwtToken = userService.loginUser(loginRequest); //authentication happens here, calling method from the UserService class
        if(jwtToken.isPresent()){
            return ResponseEntity.ok(new LoginResponse(jwtToken.get())); //ResponseEntity.ok is the 200 response
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Authentication failed")); //user not authenticated
        }
    }
}
