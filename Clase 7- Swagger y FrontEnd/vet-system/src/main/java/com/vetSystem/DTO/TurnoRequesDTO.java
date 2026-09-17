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
public class TurnoRequesDTO {

        private LocalDate fecha;
        private LocalTime hora;
        private String motivo;
        private Long idMascota;
        //private Long idVeterinaio;
}
