package com.josue.banksystem.infraestructure.adapters.exceptions;

import com.josue.banksystem.domain.exception.DuplicateUserEmailException;
import com.josue.banksystem.domain.exception.UserAlreadyDeletedException;
import com.josue.banksystem.domain.exception.UserEmailAlreadyTaken;
import com.josue.banksystem.domain.exception.UserNotFoundException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(4)
public class UserHandlerException {

    @ExceptionHandler(DuplicateUserEmailException.class)
    public ResponseEntity<String> duplicateUserEmail(DuplicateUserEmailException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyDeletedException.class)
    public  ResponseEntity<String> userAlreadyDeleted(UserAlreadyDeletedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserEmailAlreadyTaken.class)
    public ResponseEntity<String> userEmailAlreadyTaken(UserEmailAlreadyTaken ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
