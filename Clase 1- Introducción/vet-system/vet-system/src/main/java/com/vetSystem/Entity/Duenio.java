package com.vetSystem.Entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor

public class Dueno {
    private Long id;
    private String nombre;
    private String apellido;
    private String cedula;
    private Integer telefono;
    private String email;


}
