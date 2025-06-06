package com.josue.banksystem.domain.exception;

public class InsufficientAccountBalance extends RuntimeException {

    public InsufficientAccountBalance(String message) {
        super(message);
    }
}
