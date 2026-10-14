package com.patitas.veterinario.application;

import com.patitas.veterinario.domain.exception.VeterinarioNotFoundException;
import com.patitas.veterinario.domain.model.Veterinario;
import com.patitas.veterinario.domain.port.in.VeterinarioService;
import com.patitas.veterinario.domain.port.out.VeterinarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VeterinarioServiceImpl implements VeterinarioService {

    private final VeterinarioRepositoryPort veterinarioRepository;

    @Override
    public List<Veterinario> listarTodos() {
        return veterinarioRepository.buscarTodos();
    }

    @Override
    public Veterinario buscarPorId(Long id) {
        Veterinario veterinario = veterinarioRepository.buscarPorId(id);
        if (veterinario == null) {
            throw new VeterinarioNotFoundException(id);
        }
        return veterinario;
    }

    @Override
    public List<Veterinario> buscarPorEspecialidad(String especialidad) {
        return veterinarioRepository.buscarPorEspecialidad(especialidad);
    }

    @Override
    @Transactional
    public Veterinario registrar(Veterinario veterinario) {
        return veterinarioRepository.guardar(veterinario);
    }

    @Override
    @Transactional
    public Veterinario actualizar(Long id, Veterinario veterinario) {
        Veterinario existente = buscarPorId(id);
        existente.setNombre(veterinario.getNombre());
        existente.setApellido(veterinario.getApellido());
        existente.setMatricula(veterinario.getMatricula());
        existente.setEspecialidad(veterinario.getEspecialidad());
        existente.setEmail(veterinario.getEmail());
        return veterinarioRepository.guardar(existente);
    }

    @Override
    @Transactional
    public void eliminarPorId(Long id) {
        buscarPorId(id);
        veterinarioRepository.eliminarPorId(id);
    }
}
