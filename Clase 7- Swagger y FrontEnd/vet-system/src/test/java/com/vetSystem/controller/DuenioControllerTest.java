package com.vetSystem.controller;

import com.vetSystem.Controller.DuenioController;
import com.vetSystem.DTO.DuenioDTO;
import com.vetSystem.Exception.ResourceNotFoundException;
import com.vetSystem.Mapper.DuenioMapper;
import com.vetSystem.Service.DuenioService;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DuenioController.class)
public class DuenioControllerTest {
   @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
   @MockitoBean
   private DuenioService duenioService;

   @MockitoBean
   private DuenioMapper duenioMapper;

   //crear el objeto
    private DuenioDTO crearDuenioDTO() {
        DuenioDTO duenioDTO = new DuenioDTO();
        duenioDTO.setId(1L);
        duenioDTO.setNombre("Armando Esteban");
        duenioDTO.setApellido("Quito");
        duenioDTO.setCedula("1222334");
        duenioDTO.setEmail("armandoestebanquito@gmail.com");
        duenioDTO.setTelefono(11456987);
        return duenioDTO;
  }
        @Test
        void getListaVaciaStatus200() throws Exception{
            //DADO
            when(duenioService.buscarTodos()).thenReturn(List.of());
            //CUANDO+//ENTONCES
            mockMvc.perform(get("/api/duenio"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(0));
        }

        @Test
    void getDuenioIDisOk200() throws Exception{
        when(duenioService.buscarPorId(1L)).thenReturn(crearDuenioDTO());
        //CUANDO+ENTONCES
            mockMvc.perform(get("/api/duenio/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.nombre").value("Armando Esteban"));
        }

        @Test
    void getDuenioIDNotFound404() throws Exception{
        when(duenioService.buscarPorId(99L))
                .thenThrow(new ResourceNotFoundException("duenio inexistente: ",99L));
                //cuando+ entonces
            mockMvc.perform(get("/api/duenio/99"))
                    .andExpect(status().isNotFound());

        }
        @Test
    void postDuenioDesdeCuerpoBodyStatus201() throws Exception{
        //DONDE
        DuenioDTO duenio201= crearDuenioDTO();
        //Cuando
        when(duenioService.registrarDuenio(any(DuenioDTO.class))).thenReturn(duenio201);
        //cuando + entonces
        mockMvc.perform(post("/api/duenio")
                .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                .content(objectMapper.writeValueAsString(duenio201)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Armando Esteban"));
        }
    @Test
    void postDuenio_conBodyInvalido_status400() throws Exception {
        // ARRANGE
        DuenioDTO dto = crearDuenioDTO();
        dto.setEmail(""); // viola @NotBlank
        // ACT + ASSERT
        mockMvc.perform(post("/api/duenio")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}
