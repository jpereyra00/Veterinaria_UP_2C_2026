package com.patitas.mascota.domain.exception;

public class MascotaNotFoundException extends RuntimeException {
    public MascotaNotFoundException(Long id) {
        super("No se encontro la mascota con el id " + id);
    }
}
