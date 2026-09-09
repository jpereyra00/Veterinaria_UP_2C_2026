package com.vetSystem.Exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message, Long idMascota) {
        super(message);
    }
}
