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

@RestController
@RequestMapping(path = "/auth/users/") //http://localhost:9094/auth/users/
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //REGISTER USER
    @PostMapping(path = "/register/") //http://localhost:9094/auth/users/register/
    public User createUser(@RequestBody User userObject){
        return userService.createUser(userObject);
    }

    //LOGIN USER
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
