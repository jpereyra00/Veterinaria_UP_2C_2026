package com.vetSystem.DTO;

import com.vetSystem.Entity.Estado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurnoResponseDTO {

    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private String motivo;
    private Estado estado;
    private Long mascotaId;
    private String mascotaNombre;
    private Long veterinarioId;
    private String veterinarioNombre;
}
