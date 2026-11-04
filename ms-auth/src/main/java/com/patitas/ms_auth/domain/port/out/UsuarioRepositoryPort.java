package com.patitas.ms_auth.domain.port.out;

import com.patitas.ms_auth.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioRepositoryPort {

    Optional<Usuario> findByUsername(String username);
    boolean existsByUsername(String username);
    Usuario guardar(Usuario usuario);
}
