package com.patitas.ms_auth.adapter.in.web;
//adaptador de entrada: deberia ser el que expone los endopoints para la autenticacion


import com.patitas.ms_auth.adapter.in.web.dto.AuthResponse;
import com.patitas.ms_auth.adapter.in.web.dto.LoginRequest;
import com.patitas.ms_auth.adapter.in.web.dto.RegisterRequest;
import com.patitas.ms_auth.domain.port.in.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

}
