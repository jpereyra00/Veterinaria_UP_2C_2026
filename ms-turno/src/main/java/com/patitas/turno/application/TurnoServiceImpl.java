package com.patitas.turno.application;

import com.patitas.turno.domain.exception.MascotaNotFoundException;
import com.patitas.turno.domain.exception.TurnoNotFoundException;
import com.patitas.turno.domain.exception.VeterinarioNotFoundException;
import com.patitas.turno.domain.model.EstadoTurno;
import com.patitas.turno.domain.model.Turno;
import com.patitas.turno.domain.port.in.TurnoService;
import com.patitas.turno.domain.port.out.MascotaServicePort;
import com.patitas.turno.domain.port.out.TurnoRepositoryPort;
import com.patitas.turno.domain.port.out.VeterinarioServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TurnoServiceImpl implements TurnoService {

    private final TurnoRepositoryPort turnoRepository;
    // Sprint 11: puertos hacia otros microservicios (los implementan los adaptadores Feign).
    private final MascotaServicePort mascotaService;
    private final VeterinarioServicePort veterinarioService;

    @Override
    public List<Turno> listarTodos() {
        return turnoRepository.buscarTodos();
    }

    @Override
    public Turno buscarPorId(Long id) {
        Turno turno = turnoRepository.buscarPorId(id);
        if (turno == null) {
            throw new TurnoNotFoundException(id);
        }
        return turno;
    }

    @Override
    public List<Turno> buscarPorVeterinarioId(Long veterinarioId) {
        return turnoRepository.buscarPorVeterinarioId(veterinarioId);
    }

    @Override
    public List<Turno> buscarPorMascotaId(Long mascotaId) {
        return turnoRepository.buscarPorMascotaId(mascotaId);
    }

    @Override
    @Transactional
    public Turno registrar(Turno turno) {
        // Sprint 11 - Validacion cruzada: mascota y veterinario viven en OTROS microservicios.
        // Se consultan via HTTP (Feign) antes de persistir el turno.
        if (!mascotaService.existsById(turno.getMascotaId())) {
            throw new MascotaNotFoundException(turno.getMascotaId());
        }
        if (!veterinarioService.existsById(turno.getVeterinarioId())) {
            throw new VeterinarioNotFoundException(turno.getVeterinarioId());
        }
        // Regla de negocio: todo turno nace PENDIENTE
        turno.setEstado(EstadoTurno.PENDIENTE);
        return turnoRepository.guardar(turno);
    }

    @Override
    @Transactional
    public Turno cancelar(Long id) {
        Turno turno = buscarPorId(id);
        turno.setEstado(EstadoTurno.CANCELADO);
        return turnoRepository.guardar(turno);
    }
}
