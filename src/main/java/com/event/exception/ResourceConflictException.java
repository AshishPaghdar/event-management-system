package com.event.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceConflictException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceConflictException(String message) {
        super(message);
    }
}