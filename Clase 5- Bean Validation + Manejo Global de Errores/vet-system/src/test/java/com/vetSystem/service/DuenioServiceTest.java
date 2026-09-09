package com.vetSystem.service;

import com.vetSystem.DTO.DuenioDTO;
import com.vetSystem.Entity.Duenio;
import com.vetSystem.Mapper.DuenioMapper;
import com.vetSystem.Repository.DuenioRepository;
import com.vetSystem.Service.DuenioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DuenioServiceTest {
    @InjectMocks
    private DuenioService duenioService;
    @Mock
    private DuenioMapper duenioMapper;
    @Mock
    private DuenioRepository duenioRepository;

    //Test 1: Obtener todos los duenios
   @Test
    public void getAllDuenioListaVacia(){
        //DADO
        when(duenioRepository.findAll()).thenReturn(List.of());
        //CUANDO
       List<DuenioDTO> resultado= duenioService.buscarTodos();
        //ENTONCES
       assertThat(resultado.isEmpty());
       verify(duenioRepository).findAll();
    }
    //Test 2: getDuenioID exitoso
    @Test
    void getDuenioById_cuando_sea_exitosoDTO() {
        //DADO
        Duenio duenio = new Duenio();
        duenio.setId(1L);
        duenio.setNombre("Carlos");
        duenio.setApellido("Sanchez");
        duenio.setCedula("31541741");
        duenio.setTelefono(1230222);
        duenio.setEmail("carlossanchez@gmail.com");

        DuenioDTO duenioDTO = new DuenioDTO();
        duenioDTO.setId(duenio.getId());
        duenioDTO.setNombre("Carlos");
        //CUANDO
        when(duenioRepository.findById(1L)).thenReturn(Optional.of(duenio));
        when(duenioMapper.toDto(duenio)).thenReturn(duenioDTO);
        //ENTONCES
        DuenioDTO resultado = duenioService.buscarPorId(1L);
        assertThat(resultado.getNombre()).isEqualTo("Carlos");
        assertThat(resultado.getId()).isEqualTo(1L);
    }
}
