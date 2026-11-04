package com.patitas.ms_auth.domain.exception;

public class CredencialesInvalidasException extends RuntimeException {

    public CredencialesInvalidasException() {
        super("Usuario o contraseña incorrectos");
    }

    public CredencialesInvalidasException(String message) {
        super(message);
    }
}
