package com.josue.banksystem.infraestructure.adapters.exceptions;

import com.josue.banksystem.domain.exception.ClientNotFoundExcepcion;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(2)
public class ClientHandlerException {

    @ExceptionHandler(ClientNotFoundExcepcion.class)
    public ResponseEntity<String> clientNotFound(ClientNotFoundExcepcion ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
