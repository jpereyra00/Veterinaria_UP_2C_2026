package com.vetSystem.Service;

import com.vetSystem.Entity.Duenio;
import com.vetSystem.Repository.DuenioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DuenioService implements InterfaceService<Duenio> {

    private final DuenioRepository duenioRepository;


    @Override
    public Duenio registrarEntidad(Duenio duenio) {
        return duenioRepository.save(duenio);
    }

    @Override
    public Optional<Duenio> buscarPorId(Long id) {
        return duenioRepository.findById(id);
    }

    @Override
    public void eliminarEntidad(Long id) {
    Optional<Duenio> optional = duenioRepository.findById(id);
    if (optional.isPresent()) {
        duenioRepository.deleteById(id);
    }
    }

    @Override
    public Duenio modificarEntidad(Duenio duenio) {
        return duenioRepository.save(duenio);
    }

    @Override
    public List<Duenio> listarEntidades() {
        return duenioRepository.findAll();
    }

    @Override
    public Optional<Duenio> buscarPorString(String nombre) {
        return duenioRepository.findByNombre(nombre);
    }
}
