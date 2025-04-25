package com.tsg.test.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

@ControllerAdvice
public class ErrorHandler {

    // Manejo errores en validacion de peticiones
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Manejo error de violacion de integridad DB
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handledataExceptions(DataIntegrityViolationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    // Manejo error resultados no encontrados
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> handledataExceptions(EmptyResultDataAccessException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Manejo errores generales de ingreso en la BD
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handledataExceptions(DataAccessException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
