package com.josue.banksystem.infraestructure.adapters.exceptions;

import com.josue.banksystem.domain.exception.*;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestControllerAdvice
@Order(5)
public class GlobalHandlerException {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String mensaje = Optional.ofNullable(ex.getRootCause())
                .map(Throwable::getMessage)
                .orElse(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Error de formato: " + mensaje);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Map<String, String> errores = new HashMap<>();

        String mensaje = Optional.ofNullable(ex.getRootCause())
                .map(Throwable::getMessage)
                .orElse(ex.getMessage());
        errores.put("error", "Violación de integridad de datos");
        errores.put("detalle", mensaje);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errores);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
