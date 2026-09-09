package com.vetSystem.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DuenioDTO {
    private Long id;
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
