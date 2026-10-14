package com.patitas.turno.domain.port.out;

import com.patitas.turno.domain.model.Turno;

import java.util.List;

/** Puerto de salida: contrato de persistencia de Turno. */
public interface TurnoRepositoryPort {
    List<Turno> buscarTodos();
    Turno buscarPorId(Long id);
    List<Turno> buscarPorVeterinarioId(Long veterinarioId);
    List<Turno> buscarPorMascotaId(Long mascotaId);
    Turno guardar(Turno turno);
}
