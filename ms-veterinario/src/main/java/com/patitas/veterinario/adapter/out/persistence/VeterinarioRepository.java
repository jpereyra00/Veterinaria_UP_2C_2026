package com.patitas.veterinario.adapter.out.persistence;

import com.patitas.veterinario.domain.model.Veterinario;
import com.patitas.veterinario.domain.port.out.VeterinarioRepositoryPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeterinarioRepository
        extends JpaRepository<Veterinario, Long>, VeterinarioRepositoryPort {

    List<Veterinario> findByEspecialidad(String especialidad);

    @Override
    default List<Veterinario> buscarTodos() {
        return findAll();
    }

    @Override
    default Veterinario buscarPorId(Long id) {
        return findById(id).orElse(null);
    }

    @Override
    default List<Veterinario> buscarPorEspecialidad(String especialidad) {
        return findByEspecialidad(especialidad);
    }

    @Override
    default Veterinario guardar(Veterinario veterinario) {
        return save(veterinario);
    }

    @Override
    default void eliminarPorId(Long id) {
        deleteById(id);
    }
}
