package com.vetSystem.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Datos de un duenio de la clinica veterinaria")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DuenioDTO {
    @Schema(description = "Identificador unico del duenio (lo genera la base de datos)", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Schema(description = "Nombre del duenio", example = "Maria")
    @NotBlank(message = "el nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "el apellido es obligatorio")
    private String apellido;
    @Size(min = 7, max = 8, message = "El dni debe estar comprendido entre los 7 y 8 caracteres")
    private String cedula;
    private Integer telefono;
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato no es valido")
    private String email;

}
