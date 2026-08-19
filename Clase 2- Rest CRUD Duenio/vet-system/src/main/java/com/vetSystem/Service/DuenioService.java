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
public class DuenioService {

    private final DuenioRepository duenioRepository;


    @Transactional
    public Duenio registrarDuenio(Duenio duenio){
        return duenioRepository.save(duenio);
    }
    public Optional<Duenio> buscarPorId(Long id){
        return duenioRepository.findById(id);
    }
    public Optional<Duenio> buscarPorNombre(String nombre){
        return duenioRepository.findByNombre(nombre);
    }
    public List<Duenio> listarTodos(){
        return duenioRepository.findAll();
    }
    public void eliminarDuenio(Long id){
        duenioRepository.deleteById(id);
    }

}
