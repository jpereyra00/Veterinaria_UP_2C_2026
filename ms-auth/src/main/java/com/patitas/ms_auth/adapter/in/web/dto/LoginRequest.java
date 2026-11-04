package com.patitas.ms_auth.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "El username es obligatorio: ") String username,
        @NotBlank(message = "El password es obligatorio: ") String password){}

