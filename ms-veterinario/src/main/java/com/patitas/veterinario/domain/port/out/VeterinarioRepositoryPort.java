package com.patitas.veterinario.domain.port.out;

import com.patitas.veterinario.domain.model.Veterinario;

import java.util.List;

/** Puerto de salida: contrato de persistencia de Veterinario. */
public interface VeterinarioRepositoryPort {
    List<Veterinario> buscarTodos();
    Veterinario buscarPorId(Long id);
    List<Veterinario> buscarPorEspecialidad(String especialidad);
    Veterinario guardar(Veterinario veterinario);
    void eliminarPorId(Long id);
}
