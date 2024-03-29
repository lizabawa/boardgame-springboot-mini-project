package com.example.boardgamespringbootminiproject.service;

import com.example.boardgamespringbootminiproject.exception.InformationAlreadyExistsException;
import com.example.boardgamespringbootminiproject.model.User;
import com.example.boardgamespringbootminiproject.model.request.LoginRequest;
import com.example.boardgamespringbootminiproject.repository.UserRepository;
import com.example.boardgamespringbootminiproject.security.JWTUtils;
import com.example.boardgamespringbootminiproject.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class containing business logic for user registration and login.
 */
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    /**
     * This class contains the business logic for Registering and logging in a user
     * @param userRepository This is the repository that contains all users details.
     * @param passwordEncoder This encodes a password.
     * @param jwtUtils This is the JWTUtils class we created that generates and validates JWT tokens upon CRUD requests.
     * @param authenticationManager This is used for authentication.
     */
    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, JWTUtils jwtUtils, @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Registers a new user.
     *
     * @param userObject The user object to be registered.
     * @return The registered user.
     */
    public User createUser(User userObject){
        if(!userRepository.existsByEmailAddress(userObject.getEmailAddress())){
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        }else {
            throw new InformationAlreadyExistsException("A user with the email " + userObject.getEmailAddress() + " already exists.");
        }
    }

    /**
     * Logs in a user and returns a JWT token upon successful authentication.
     *
     * @param loginRequest The login request containing email address and password.
     * @return An optional JWT token if authentication is successful, empty otherwise.
     */
    public Optional<String> loginUser(LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmailAddress(), loginRequest.getPassword()); //grab user login details (email and password)

        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken); //authenticating user based on the info grabbed
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
            return Optional.of(jwtUtils.generateJwtToken(myUserDetails));

        }catch (Exception e){
            return Optional.empty();
        }
    }

    /**
     * Finds a user by their email address.
     *
     * @param emailAddress The email address of the user to find.
     * @return The user with the specified email address.
     */
    public User findUserByEmailAddress(String emailAddress){
        return userRepository.findUserByEmailAddress(emailAddress);
    }
}
