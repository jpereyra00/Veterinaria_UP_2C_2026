package com.patitas.turno.adapter.in.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO de ENTRADA de Turno: lo que el cliente HTTP envia al registrar un turno.
 * mascotaId y veterinarioId son referencias por ID a OTROS microservicios:
 * su existencia se valida via Feign en TurnoServiceImpl (Sprint 11).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurnoRequest {

    @NotNull(message = "La fechaHora es obligatoria")
    private LocalDateTime fechaHora;

    @NotNull(message = "El mascotaId es obligatorio")
    private Long mascotaId;

    @NotNull(message = "El veterinarioId es obligatorio")
    private Long veterinarioId;

    private String motivo;
}
