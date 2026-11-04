package com.patitas.ms_auth.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank(message = "El username es obligatorio: ") String username,
        @NotBlank(message = "El password es obligatorio: ") String password,
        @NotBlank(message = "El rol es obligatorio: ") String rol ) {
}
