package com.vetSystem.Mapper;

import com.vetSystem.DTO.VeterinarioDTO;
import com.vetSystem.Entity.Veterinario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VeterinarioMapper {
    VeterinarioDTO toDto(Veterinario veterinario);
    Veterinario toEntity(VeterinarioDTO veterinarioDTO);
}
