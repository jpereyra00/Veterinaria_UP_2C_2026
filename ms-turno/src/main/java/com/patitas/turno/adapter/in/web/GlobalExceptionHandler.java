package com.patitas.turno.adapter.in.web;

import com.patitas.turno.domain.exception.MascotaNotFoundException;
import com.patitas.turno.domain.exception.ServicioNoDisponibleException;
import com.patitas.turno.domain.exception.TurnoNotFoundException;
import com.patitas.turno.domain.exception.VeterinarioNotFoundException;
import feign.FeignException;
import feign.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Traduce las excepciones de ms-turno a codigos HTTP + un ErrorResponse uniforme.
 * Incluye los casos propios del Sprint 11: mascota/veterinario inexistentes (404)
 * y fallos de la comunicacion Feign (servicio remoto caido/timeout -> 503).
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /** Turno inexistente -> 404 Not Found */
    @ExceptionHandler(TurnoNotFoundException.class)
    public ResponseEntity<ErrorResponse> manejarTurno(TurnoNotFoundException ex,
                                                      HttpServletRequest request) {
        return construir(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    /** La mascota referenciada no existe en ms-mascota -> 404 Not Found */
    @ExceptionHandler(MascotaNotFoundException.class)
    public ResponseEntity<ErrorResponse> manejarMascota(MascotaNotFoundException ex,
                                                        HttpServletRequest request) {
        return construir(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    /** El veterinario referenciado no existe en ms-veterinario -> 404 Not Found */
    @ExceptionHandler(VeterinarioNotFoundException.class)
    public ResponseEntity<ErrorResponse> manejarVeterinario(VeterinarioNotFoundException ex,
                                                            HttpServletRequest request) {
        return construir(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    /** Fallos de @Valid (DTO invalido) -> 400 Bad Request */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> manejarValidacion(MethodArgumentNotValidException ex,
                                                           HttpServletRequest request) {
        String detalle = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return construir(HttpStatus.BAD_REQUEST, detalle, request);
    }

    /**
     * Error generico de Feign (servicio remoto caido, timeout, 5xx del otro MS)
     * -> 503 Service Unavailable. El cliente sabe que el problema es de infraestructura.
     */
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> manejarFeign(FeignException ex,
                                                      HttpServletRequest request) {
        return construir(HttpStatus.SERVICE_UNAVAILABLE,
                "Servicio remoto no disponible: " + ex.getMessage(), request);
    }
    @ExceptionHandler(ServicioNoDisponibleException.class)
    public ResponseEntity<ErrorResponse> manejarServicioNoDisponible(ServicioNoDisponibleException ex,HttpServletRequest request){
        return construir(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage(), request);
    }


    /** Cualquier otra excepcion no prevista -> 500 Internal Server Error */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> manejarGenerico(Exception ex,
                                                         HttpServletRequest request) {
        return construir(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request);
    }

    private ResponseEntity<ErrorResponse> construir(HttpStatus status, String mensaje,
                                                    HttpServletRequest request) {
        ErrorResponse body = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                mensaje,
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(body);
    }
}
