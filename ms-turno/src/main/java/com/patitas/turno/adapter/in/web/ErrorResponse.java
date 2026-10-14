package com.patitas.turno.adapter.in.web;

import java.time.LocalDateTime;

/**
 * Cuerpo estandar de error que devuelve la API ante cualquier excepcion.
 * Misma forma en todos los microservicios.
 */
public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String mensaje,
        String path
) {
}
