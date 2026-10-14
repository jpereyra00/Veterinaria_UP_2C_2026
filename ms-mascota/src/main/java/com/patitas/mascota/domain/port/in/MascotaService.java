package com.patitas.mascota.domain.port.in;

import com.patitas.mascota.domain.model.Mascota;

import java.util.List;

/** Puerto de entrada: el contrato de casos de uso de Mascota (qué, no cómo). */
public interface MascotaService {
    List<Mascota> listarTodos();
    Mascota buscarPorId(Long id);
    List<Mascota> buscarPorDuenioId(Long duenioId);
    Mascota registrar(Mascota mascota);
    Mascota actualizar(Long id, Mascota mascota);
    void eliminarPorId(Long id);
}
