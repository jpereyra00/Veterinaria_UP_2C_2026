package com.patitas.turno.adapter.out.feign;

import lombok.Data;

/**
 * DTO de CONSUMO: copia reducida del JSON que devuelve ms-veterinario.
 * Clase PROPIA de ms-turno (no se importa la de ms-veterinario).
 */
@Data
public class VeterinarioResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private String especialidad;
}
