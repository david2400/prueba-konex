package com.drogeria.backend.exceptions;

public class ApiRequestException extends RuntimeException{

    public ApiRequestException(String message) {
        super(message);
    }
}
