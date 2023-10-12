package com.example.boardgamespringbootminiproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class handles exceptions for if information already exists.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class InformationAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new InformationAlreadyExistsException with the specified error message.
     *
     * @param message The error message that describes why the exception was thrown.
     */
    public InformationAlreadyExistsException(String message){
        super(message);
    }
}
