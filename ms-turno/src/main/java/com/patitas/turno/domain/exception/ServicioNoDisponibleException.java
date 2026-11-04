package com.patitas.turno.domain.exception;

public class ServicioNoDisponibleException extends RuntimeException {
    public ServicioNoDisponibleException(String message) {
        super("El servicio: "+message+ " no esta disponible en este momento, Intenta nuevamente en unos minutos");
    }
}
