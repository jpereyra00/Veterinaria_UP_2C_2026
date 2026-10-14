package com.patitas.mascota.application;

import com.patitas.mascota.domain.exception.MascotaNotFoundException;
import com.patitas.mascota.domain.model.Mascota;
import com.patitas.mascota.domain.port.in.MascotaService;
import com.patitas.mascota.domain.port.out.MascotaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Lógica de negocio de Mascota. Depende SOLO de puertos (in/out), nunca de adapter.*
 * Así se respeta la Dependency Rule de la arquitectura hexagonal.
 */
@Service
@RequiredArgsConstructor
public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepositoryPort mascotaRepository;

    @Override
    public List<Mascota> listarTodos() {
        return mascotaRepository.buscarTodos();
    }

    @Override
    public Mascota buscarPorId(Long id) {
        Mascota mascota = mascotaRepository.buscarPorId(id);
        if (mascota == null) {
            throw new MascotaNotFoundException(id);
        }
        return mascota;
    }

    @Override
    public List<Mascota> buscarPorDuenioId(Long duenioId) {
        return mascotaRepository.buscarPorDuenioId(duenioId);
    }

    @Override
    @Transactional
    public Mascota registrar(Mascota mascota) {
        return mascotaRepository.guardar(mascota);
    }

    @Override
    @Transactional
    public Mascota actualizar(Long id, Mascota mascota) {
        Mascota existente = buscarPorId(id);
        existente.setNombre(mascota.getNombre());
        existente.setEspecie(mascota.getEspecie());
        existente.setRaza(mascota.getRaza());
        existente.setFechaNacimiento(mascota.getFechaNacimiento());
        existente.setDuenioId(mascota.getDuenioId());
        return mascotaRepository.guardar(existente);
    }

    @Override
    @Transactional
    public void eliminarPorId(Long id) {
        buscarPorId(id);
        mascotaRepository.eliminarPorId(id);
    }
}
