package adapter;

import domain.exception.DuenioNotFoundException;
import domain.exception.DuplicateEmailException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(DuenioNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDuenioNotFoundException(DuenioNotFoundException rnfe, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(LocalDateTime.now(), 404," Not Found", rnfe.getMessage(), request.getRequestURI()));
            }
     @ExceptionHandler(DuplicateEmailException.class)
     public ResponseEntity<ErrorResponse> handleDuplicateEmailException(DuplicateEmailException dre, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(LocalDateTime.now(), 409," Conflict", dre.getMessage(), request.getRequestURI()));
     }
}
