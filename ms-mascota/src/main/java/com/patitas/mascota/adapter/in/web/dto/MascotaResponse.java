package com.patitas.mascota.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO de SALIDA de Mascota: lo que la API devuelve al cliente.
 * Incluye el id (ya generado). Este es el JSON que ms-turno consumira via Feign.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MascotaResponse {
    private Long id;
    private String nombre;
    private String especie;
    private String raza;
    private LocalDate fechaNacimiento;
    private Long duenioId;
}
