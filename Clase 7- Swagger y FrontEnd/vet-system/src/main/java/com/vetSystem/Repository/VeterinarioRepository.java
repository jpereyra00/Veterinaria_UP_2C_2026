package com.vetSystem.Repository;

import com.vetSystem.Entity.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
    boolean existsByMatricula(String matricula);
    Optional<Veterinario> findByMatricula(String matricula);
}
