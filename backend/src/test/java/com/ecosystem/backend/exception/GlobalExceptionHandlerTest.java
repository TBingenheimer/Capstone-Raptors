package com.ecosystem.backend.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GlobalExceptionHandlerTest {
    @Test
    void globalExceptionHandlerTest(){
        Exception ex = new RuntimeException("Something went wrong");
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResponseEntity<ExceptionMessage> response = handler.handleInternalServerException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Internal server error", response.getBody().message());
        assertNotNull(response.getBody().timestamp());
    }
}
