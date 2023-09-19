package com.example.boardgamespringbootminiproject.controller;

import com.example.boardgamespringbootminiproject.model.User;
import com.example.boardgamespringbootminiproject.model.request.LoginRequest;
import com.example.boardgamespringbootminiproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth/users/") //http://localhost:9094/auth/users/
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //Register
    @PostMapping(path = "/register/") //http://localhost:9094/auth/users/register/
    public User createUser(@RequestBody User userObject){
        return userService.createUser(userObject);
    }
}
