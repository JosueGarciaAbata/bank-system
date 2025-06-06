package com.josue.banksystem.domain.exception;

public class UserAlreadyDeletedException extends RuntimeException {

    public UserAlreadyDeletedException(String message) {
        super(message);
    }
}


