package com.vetSystem.Exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String recurso, Long id) {
        super(recurso + " no encontrado con id " + id);
    }
}
