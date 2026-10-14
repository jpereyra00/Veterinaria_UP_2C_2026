package com.patitas.mascota.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entidad de dominio Mascota.
 * OJO: duenioId es un Long, NO un @ManyToOne hacia Duenio.
 * En microservicios cada servicio tiene su propia BD: no hay foreign key cruzada.
 * La referencia a otro bounded context se guarda por ID y se valida vía HTTP (Sprint 13).
 */
@Entity
@Table(name = "mascotas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String especie;

    private String raza;

    private LocalDate fechaNacimiento;

    @Column(nullable = false)
    private Long duenioId;   // referencia por ID al ms-duenio (sin @ManyToOne)
}
