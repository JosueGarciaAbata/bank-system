package com.josue.banksystem.infraestructure.adapters.exceptions;

import com.josue.banksystem.domain.exception.AccountNotFoundException;
import com.josue.banksystem.domain.exception.InsufficientAccountBalance;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(1)
public class AccountHandlerException {


    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<String> handleAccountNotFound(AccountNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientAccountBalance.class)
    public ResponseEntity<String> insufficientAccountBalance(InsufficientAccountBalance ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
