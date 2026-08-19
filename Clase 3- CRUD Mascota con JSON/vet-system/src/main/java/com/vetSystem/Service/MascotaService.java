package com.vetSystem.Service;

import com.vetSystem.Entity.Mascota;
import com.vetSystem.Repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService implements InterfaceService<Mascota> {

    @Autowired
    private MascotaRepository mascotaRepository;
    @Override
    public Mascota registrarEntidad(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
    public Optional<Mascota> buscarPorId(Long id) {
        return mascotaRepository.findById(id);
    }

    @Override
    public void eliminarEntidad(Long id) {
        mascotaRepository.deleteById(id);
    }

    @Override
    public Mascota modificarEntidad(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
    public List<Mascota> listarEntidades() {
        return mascotaRepository.findAll();
    }

    @Override
    public Optional<Mascota> buscarPorString(String nombre) {
        return mascotaRepository.findByNombreIgnoreCase(nombre) ;
    }
}
