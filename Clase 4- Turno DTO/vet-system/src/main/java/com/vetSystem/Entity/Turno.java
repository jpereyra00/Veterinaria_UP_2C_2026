package com.vetSystem.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "turnos")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate fecha;
    @Column
    private LocalTime hora;
    @Column
    private String motivo;
    @Column
    private  Estado turnoEstado;
    @ManyToOne(fetch = FetchType.LAZY)
    private Mascota mascota;
   /* @ManyToOne(fetch = FetchType.LAZY)
    private Veterinario veterinario;*/

}
