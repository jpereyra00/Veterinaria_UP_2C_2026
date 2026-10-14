package com.patitas.turno.domain.port.in;

import com.patitas.turno.domain.model.Turno;

import java.util.List;

/** Puerto de entrada: casos de uso de Turno. */
public interface TurnoService {
    List<Turno> listarTodos();
    Turno buscarPorId(Long id);
    List<Turno> buscarPorVeterinarioId(Long veterinarioId);
    List<Turno> buscarPorMascotaId(Long mascotaId);
    Turno registrar(Turno turno);
    Turno cancelar(Long id);
}
