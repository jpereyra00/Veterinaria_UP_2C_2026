package com.vetSystem.Service;

import com.vetSystem.Entity.Duenio;
import com.vetSystem.Repository.DuenioRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class DuenioService {
    private final DuenioRepository duenioRepository;



    public Duenio registrarDuenio(Duenio duenio){
        return duenioRepository.save(duenio);
    }
    public Optional<Duenio> buscarPorId(Long id){
        return duenioRepository.findById(id);
    }
    public List<Duenio> listarTodos(){
        return duenioRepository.findAll();
    }
    public void eliminarDuenio(Long id){
        duenioRepository.deleteById(id);
    }

}
