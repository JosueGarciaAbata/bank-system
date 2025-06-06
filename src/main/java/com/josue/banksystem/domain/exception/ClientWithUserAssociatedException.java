package com.josue.banksystem.domain.exception;

public class ClientWithUserAssociatedException extends RuntimeException {

    public ClientWithUserAssociatedException(String message) {
        super(message);
    }
}
