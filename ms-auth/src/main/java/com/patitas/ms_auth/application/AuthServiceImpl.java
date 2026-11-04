package com.patitas.ms_auth.application;

import com.patitas.ms_auth.adapter.in.web.dto.AuthResponse;
import com.patitas.ms_auth.adapter.in.web.dto.LoginRequest;
import com.patitas.ms_auth.adapter.in.web.dto.RegisterRequest;
import com.patitas.ms_auth.domain.exception.CredencialesInvalidasException;
import com.patitas.ms_auth.domain.exception.UsuarioYaExisteException;
import com.patitas.ms_auth.domain.model.Usuario;
import com.patitas.ms_auth.domain.port.in.AuthService;
import com.patitas.ms_auth.domain.port.out.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepositoryPort usuarioRepository;
    private final PasswordEncoder passwordEncoder;   // BCrypt (bean de SecurityConfig)
    private final JwtService jwtService;


    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (usuarioRepository.existsByUsername(request.username())) {
            throw new UsuarioYaExisteException(request.username());
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(request.username());
        usuario.setPassword(passwordEncoder.encode(request.password())); // <-- hash BCrypt
        usuario.setRol(request.rol());
        usuarioRepository.guardar(usuario);

        String token = jwtService.generateToken(usuario.getUsername(), usuario.getRol());
        return new AuthResponse(token);
    }


    @Override
    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByUsername(request.username())
                .orElseThrow(CredencialesInvalidasException::new);

        if (!passwordEncoder.matches(request.password(), usuario.getPassword())) {
            throw new CredencialesInvalidasException();
        }

        String token = jwtService.generateToken(usuario.getUsername(), usuario.getRol());
        return new AuthResponse(token);
    }
}
