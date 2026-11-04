package com.patitas.ms_auth.adapter.in.web.out.persistance;

import com.patitas.ms_auth.domain.model.Usuario;
import com.patitas.ms_auth.domain.port.out.UsuarioRepositoryPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryPort {

    Optional<Usuario> findByUsername(String username);

    boolean existsByUsername(String username);

    @Override
    default Usuario guardar(Usuario usuario) {
        return save(usuario);
    }
}
