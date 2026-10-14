package com.patitas.veterinario.domain.exception;

public class VeterinarioNotFoundException extends RuntimeException {
    public VeterinarioNotFoundException(Long id) {
        super("No se encontro el veterinario con el id " + id);
    }
}
