package com.josue.banksystem.domain.exception;

public class ClientWithAccountsAssociatedException extends RuntimeException {

    public ClientWithAccountsAssociatedException(String message) {
        super(message);
    }
}
