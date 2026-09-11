package com.vetSystem.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeterinarioDTO {
    private Long id;
    @NotBlank(message = "el nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "el apellido es obligatorio")
    private String apellido;
    @NotBlank(message = "la matricula es obligatoria")
    private String matricula;
    private String especialidad;
    @Email(message = "El formato no es valido")
    private String email;
}
