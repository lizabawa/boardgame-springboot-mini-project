package com.example.boardgamespringbootminiproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class handles exceptions for if information already exists.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class InformationAlreadyExistsException extends RuntimeException {
    public InformationAlreadyExistsException(String message){
        super(message);
    }
}
