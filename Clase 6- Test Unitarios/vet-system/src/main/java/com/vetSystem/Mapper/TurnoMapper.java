package com.vetSystem.Mapper;

import com.vetSystem.DTO.TurnoResponseDTO;
import com.vetSystem.Entity.Turno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TurnoMapper {
    @Mapping(source = "turnoEstado", target = "estado")
    @Mapping(source = "mascota.id", target = "mascotaId")
    @Mapping(source = "mascota.nombre", target = "mascotaNombre")
    @Mapping(source = "veterinario.id", target = "veterinarioId")
    @Mapping(source = "veterinario.nombre", target = "veterinarioNombre")
    TurnoResponseDTO toDto(Turno turno);
}
