package com.patitas.turno.domain.exception;

/**
 * Excepcion de dominio: el veterinario referenciado por el turno no existe en ms-veterinario.
 * Excepcion PROPIA de ms-turno (no se importa la de ms-veterinario). Dominio puro.
 */
public class VeterinarioNotFoundException extends RuntimeException {
    public VeterinarioNotFoundException(Long id) {
        super("Veterinario no encontrado: " + id);
    }
}
