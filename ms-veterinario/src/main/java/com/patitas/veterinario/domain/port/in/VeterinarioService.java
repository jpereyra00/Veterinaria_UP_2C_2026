package com.patitas.veterinario.domain.port.in;

import com.patitas.veterinario.domain.model.Veterinario;

import java.util.List;

/** Puerto de entrada: casos de uso de Veterinario. */
public interface VeterinarioService {
    List<Veterinario> listarTodos();
    Veterinario buscarPorId(Long id);
    List<Veterinario> buscarPorEspecialidad(String especialidad);
    Veterinario registrar(Veterinario veterinario);
    Veterinario actualizar(Long id, Veterinario veterinario);
    void eliminarPorId(Long id);
}
