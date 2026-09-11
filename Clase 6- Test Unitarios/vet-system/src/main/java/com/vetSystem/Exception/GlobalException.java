package com.vetSystem.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException extends RuntimeException {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(LocalDateTime.now(), 404," Not Found", rnfe.getMessage(), request.getRequestURI()));
            }
     @ExceptionHandler(DuplicateResourceException.class)
     public ResponseEntity<ErrorResponse> handleDuplicateResourceException(DuplicateResourceException dre, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(LocalDateTime.now(), 409," Conflict", dre.getMessage(), request.getRequestURI()));
     }
     @ExceptionHandler(TurnoSuperpuestoException.class)
     public ResponseEntity<ErrorResponse> handleTurnoSuperpuestoException(TurnoSuperpuestoException tse, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(LocalDateTime.now(), 409," Conflict", tse.getMessage(), request.getRequestURI()));
     }
     /*@ExceptionHandler(Exception.class)
     public ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
   */
}
