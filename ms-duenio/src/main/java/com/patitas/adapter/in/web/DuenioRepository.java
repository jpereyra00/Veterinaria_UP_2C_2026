package com.patitas.adapter.in.web;

import domain.model.Duenio;
import domain.port.out.DuenioRepositoryPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Adaptador de persistencia: implementa el puerto de salida del dominio
 * ({@link DuenioRepositoryPort}) apoyandose en Spring Data JPA.
 * Los finders se resuelven con JPQL para conservar los nombres en espanol,
 * y las operaciones CRUD delegan en los metodos heredados de JpaRepository.
 */
@Repository
public interface DuenioRepository extends JpaRepository<Duenio, Long>, DuenioRepositoryPort {

    @Override
    @Query("SELECT d FROM Duenio d WHERE d.nombre = :nombre")
    Optional<Duenio> buscarPorNombre(@Param("nombre") String nombre);

    @Override
    @Query("SELECT d FROM Duenio d WHERE d.email = :email")
    Optional<Duenio> buscarPorEmail(@Param("email") String email);

    @Override
    @Query("SELECT d FROM Duenio d WHERE d.nombre = :nombre AND d.apellido = :apellido")
    Optional<Duenio> buscarPorNombreYApellido(@Param("nombre") String nombre,
                                              @Param("apellido") String apellido);

    @Override
    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Duenio d WHERE d.email = :email")
    boolean existePorEmail(@Param("email") String email);

    @Override
    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Duenio d WHERE d.nombre = :nombre")
    boolean existePorNombre(@Param("nombre") String nombre);

    @Override
    default List<Duenio> buscarTodos() {
        return findAll();
    }

    @Override
    default Duenio guardar(Duenio duenio) {
        return save(duenio);
    }

    @Override
    default Duenio buscarPorId(Long id) {
        return findById(id).orElse(null);
    }

    @Override
    default void eliminarPorId(Long id) {
        deleteById(id);
    }
}
