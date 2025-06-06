package com.josue.banksystem.domain.exception;

public class DuplicateUserEmailException extends RuntimeException {

    public DuplicateUserEmailException(String message) {
        super(message);
    }
}
