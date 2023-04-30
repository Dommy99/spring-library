package com.bookkeeper.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InformationExistException extends RuntimeException {
    /**
     * Constructor for InformationExistException
     *
     * @param message the error message for the exception
     */
    public InformationExistException(String message) {
        super(message);
    }
}
