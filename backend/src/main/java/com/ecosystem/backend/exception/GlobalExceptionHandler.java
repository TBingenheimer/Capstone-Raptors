package com.ecosystem.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // explizit ResponseStatusException behandeln
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
        log.warn("Handled ResponseStatusException: {}", ex.getReason());
        return ResponseEntity.status(ex.getStatusCode())
                .body(new ExceptionMessage(ex.getReason(), LocalDateTime.now()));
    }

    // Catch-all fallback NUR für echte unerwartete Fehler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionMessage> handleInternalServerException(Exception ex) {
        log.error("Unexpected error occurred", ex);
        return ResponseEntity
                .status(500)
                .body(new ExceptionMessage("Internal server error", LocalDateTime.now()));
    }
}
