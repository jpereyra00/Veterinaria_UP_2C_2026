package com.patitas.ms_auth.domain.port.in;

import com.patitas.ms_auth.adapter.in.web.dto.AuthResponse;
import com.patitas.ms_auth.adapter.in.web.dto.LoginRequest;
import com.patitas.ms_auth.adapter.in.web.dto.RegisterRequest;
import jakarta.validation.Valid;

//PUERTO DE ENTRADA
public interface AuthService {

    AuthResponse register(@Valid RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
