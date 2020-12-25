package com.rustam.earthquakes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UsgsResourceNotFoundException extends RuntimeException{
    public UsgsResourceNotFoundException(){
        super();
    }

    public UsgsResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsgsResourceNotFoundException(String message) {
        super(message);
    }

    public UsgsResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
