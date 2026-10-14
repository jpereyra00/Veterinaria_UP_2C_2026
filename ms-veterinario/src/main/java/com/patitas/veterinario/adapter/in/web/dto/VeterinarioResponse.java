package com.patitas.veterinario.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de SALIDA de Veterinario: lo que la API devuelve al cliente.
 * Este es el JSON que ms-turno consumira via Feign.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeterinarioResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;
    private String especialidad;
    private String email;
}
