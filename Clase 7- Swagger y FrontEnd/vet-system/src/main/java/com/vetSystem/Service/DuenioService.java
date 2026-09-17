package com.vetSystem.Service;

import com.vetSystem.DTO.DuenioDTO;
import com.vetSystem.Entity.Duenio;
import com.vetSystem.Entity.Estado;
import com.vetSystem.Entity.Turno;
import com.vetSystem.Exception.DuplicateResourceException;
import com.vetSystem.Exception.ResourceNotFoundException;
import com.vetSystem.Mapper.DuenioMapper;
import com.vetSystem.Repository.DuenioRepository;
import com.vetSystem.Repository.TurnoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DuenioService {
    @Autowired
    private DuenioRepository duenioRepository;

    @Autowired
    private DuenioMapper duenioMapper;

    @Transactional
    public DuenioDTO registrarDuenio(DuenioDTO duenioDTO) {
        if(duenioRepository.existsByEmail(duenioDTO.getEmail())) {
            throw new DuplicateResourceException("El duenio ya existe");
        }
        Duenio duenio= duenioMapper.toEntity(duenioDTO);
        return duenioMapper.toDto(duenioRepository.save(duenio));
    }
    @Transactional
    public DuenioDTO buscarPorId(Long id) {
        Optional<Duenio> duenioBuscado= duenioRepository.findById(id);
        return duenioMapper.toDto(duenioBuscado.get());
    }
    public List<DuenioDTO> buscarTodos(){
        List<Duenio> listaDuenios= duenioRepository.findAll();
        List<DuenioDTO> listaDTO= new ArrayList<>();
        for (Duenio duenio : listaDuenios) {
            listaDTO.add(duenioMapper.toDto(duenio));
                    }
        return listaDTO;
    }

}
