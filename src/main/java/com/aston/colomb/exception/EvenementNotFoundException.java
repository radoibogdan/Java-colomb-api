package com.aston.colomb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EvenementNotFoundException extends RuntimeException {
    public EvenementNotFoundException(String message) {
        super(message);
    }
}
