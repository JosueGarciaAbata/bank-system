package com.josue.banksystem.domain.exception;

public class UserEmailAlreadyTaken extends RuntimeException {

    public UserEmailAlreadyTaken(String message) {
        super(message);
    }
}
