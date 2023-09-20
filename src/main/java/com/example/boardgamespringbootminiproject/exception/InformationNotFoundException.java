package com.example.boardgamespringbootminiproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class handles exceptions for if information is not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InformationNotFoundException extends RuntimeException {
    public InformationNotFoundException(String message){
        super(message);
    }
}
