package com.example.boardgamespringbootminiproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class handles exceptions for if information is not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InformationNotFoundException extends RuntimeException {

    /**
     * Constructs a new InformationNotFoundException with the specified error message.
     *
     * @param message The error message that describes why the exception was thrown.
     */
    public InformationNotFoundException(String message){
        super(message);
    }
}
