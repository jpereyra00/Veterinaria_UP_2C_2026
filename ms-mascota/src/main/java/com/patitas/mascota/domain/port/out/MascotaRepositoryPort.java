package com.patitas.mascota.domain.port.out;

import com.patitas.mascota.domain.model.Mascota;

import java.util.List;

/** Puerto de salida: lo que el dominio necesita de la persistencia (sin saber de JPA). */
public interface MascotaRepositoryPort {
    List<Mascota> buscarTodos();
    Mascota buscarPorId(Long id);
    List<Mascota> buscarPorDuenioId(Long duenioId);
    Mascota guardar(Mascota mascota);
    void eliminarPorId(Long id);
}
