package com.josue.banksystem.infraestructure.adapters.exceptions;

import com.josue.banksystem.domain.exception.RoleNotFoundException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(3)
public class RoleHandlerException {

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<String> roleNotFound(RoleNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
