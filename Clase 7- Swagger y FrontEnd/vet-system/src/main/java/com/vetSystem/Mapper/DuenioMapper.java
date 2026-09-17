package com.vetSystem.Mapper;

import com.vetSystem.DTO.DuenioDTO;
import com.vetSystem.Entity.Duenio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DuenioMapper {
    DuenioDTO toDto(Duenio duenio);
    Duenio toEntity(DuenioDTO duenioDTO);
}
