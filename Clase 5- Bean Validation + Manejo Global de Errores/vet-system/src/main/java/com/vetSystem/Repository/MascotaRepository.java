package com.vetSystem.Repository;

import com.vetSystem.Entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    Optional<Mascota> findByNombre(String nombre); //PICHA DIFERENTE //picha
    Optional<Mascota> findByNombreIgnoreCase(String nombre); //Filhia //FILHIA
    Optional<Mascota> findByRazaIgnoreCase(String raza);
    List<Mascota> findByDuenioId(Long DuenioId);
    boolean existsByNombreAndDuenioId(String nombre, Long DuenioId);
    long countByEspecie(String especie); //prueben con y sin ignoreCase

}
