package com.patitas.turno.domain.exception;

/**
 * Excepcion de dominio: la mascota referenciada por el turno no existe en ms-mascota.
 * Es una excepcion PROPIA de ms-turno (no se importa la de ms-mascota: los servicios
 * no comparten codigo de dominio). Sin imports de Spring/HTTP: el dominio es puro.
 */
public class MascotaNotFoundException extends RuntimeException {
    public MascotaNotFoundException(Long id) {
        super("Mascota no encontrada: " + id);
    }
}
