package com.patitas.mascota.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO de ENTRADA de Mascota: lo que el cliente HTTP envia al crear/actualizar.
 * No es la entidad: no tiene id (lo genera la BD) y lleva las validaciones de @Valid.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MascotaRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La especie es obligatoria")
    private String especie;

    private String raza;

    private LocalDate fechaNacimiento;

    @NotNull(message = "El duenioId es obligatorio")
    private Long duenioId;
}
