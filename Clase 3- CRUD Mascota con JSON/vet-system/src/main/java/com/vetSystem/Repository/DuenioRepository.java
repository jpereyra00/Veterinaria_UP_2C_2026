package com.vetSystem.Repository;

import com.vetSystem.Entity.Duenio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DuenioRepository extends JpaRepository<Duenio,Long> {
 //quiero buscar por nombre HQL
    Optional<Duenio> findByNombre(String nombre);
    Optional<Duenio> findByEmail(String email);
    Optional<Duenio> findByNombreAndApellido(String nombre, String apellido);
}
