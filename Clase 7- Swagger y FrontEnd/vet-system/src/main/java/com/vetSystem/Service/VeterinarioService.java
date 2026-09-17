package com.vetSystem.Service;

import com.vetSystem.DTO.VeterinarioDTO;
import com.vetSystem.Entity.Veterinario;
import com.vetSystem.Exception.DuplicateResourceException;
import com.vetSystem.Exception.ResourceNotFoundException;
import com.vetSystem.Mapper.VeterinarioMapper;
import com.vetSystem.Repository.VeterinarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VeterinarioService {

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private VeterinarioMapper veterinarioMapper;

    @Transactional
    public VeterinarioDTO registrarVeterinario(VeterinarioDTO veterinarioDTO) {
        if (veterinarioRepository.existsByMatricula(veterinarioDTO.getMatricula())) {
            throw new DuplicateResourceException(
                    "La matricula ya esta registrada: " + veterinarioDTO.getMatricula());
        }
        Veterinario veterinario = veterinarioMapper.toEntity(veterinarioDTO);
        return veterinarioMapper.toDto(veterinarioRepository.save(veterinario));
    }

    public VeterinarioDTO buscarPorId(Long id) {
        Veterinario veterinario = veterinarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veterinario", id));
        return veterinarioMapper.toDto(veterinario);
    }

    public List<VeterinarioDTO> buscarTodos() {
        List<VeterinarioDTO> listaDTO = new ArrayList<>();
        for (Veterinario veterinario : veterinarioRepository.findAll()) {
            listaDTO.add(veterinarioMapper.toDto(veterinario));
        }
        return listaDTO;
    }
}
