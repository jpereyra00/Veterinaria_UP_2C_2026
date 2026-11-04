package com.patitas.ms_auth.domain.exception;

public class UsuarioYaExisteException extends RuntimeException {

    public UsuarioYaExisteException(String username) {
        super("El usuario '" + username + "' ya existe");
    }
}
