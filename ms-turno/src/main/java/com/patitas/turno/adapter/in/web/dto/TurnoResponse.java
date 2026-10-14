package com.patitas.turno.adapter.in.web.dto;

import com.patitas.turno.domain.model.EstadoTurno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO de SALIDA de Turno: lo que la API devuelve al cliente.
 * Incluye el id y el estado (ya calculado por el dominio).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurnoResponse {
    private Long id;
    private LocalDateTime fechaHora;
    private Long mascotaId;
    private Long veterinarioId;
    private String motivo;
    private EstadoTurno estado;
}
