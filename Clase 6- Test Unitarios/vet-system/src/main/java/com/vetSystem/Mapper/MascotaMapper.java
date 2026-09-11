package com.vetSystem.Mapper;

import com.vetSystem.DTO.MascotaDTO;
import com.vetSystem.Entity.Mascota;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MascotaMapper {

    @Mapping(source = "duenio.id", target = "idDuenio")
    @Mapping(source ="duenio.nombre", target = "duenioNombre")
    MascotaDTO toDto(Mascota mascota);
}
