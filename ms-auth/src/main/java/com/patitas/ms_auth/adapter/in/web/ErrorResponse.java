package com.patitas.ms_auth.adapter.in.web;

import java.time.LocalDateTime;

//cuerpo estandar
public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String mensaje,
        String path

) {
}
