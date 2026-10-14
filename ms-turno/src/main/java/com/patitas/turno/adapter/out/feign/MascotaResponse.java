package com.patitas.turno.adapter.out.feign;

import lombok.Data;

/**
 * DTO de CONSUMO: copia reducida del JSON que devuelve ms-mascota.
 * Solo los campos que ms-turno necesita. Es una clase PROPIA de ms-turno:
 * NO se importa la Mascota de ms-mascota (los microservicios no comparten dominio).
 * Tiene el mismo nombre simple que el MascotaResponse de ms-mascota, pero vive en
 * otro paquete y otro modulo: son dos clases distintas e independientes.
 */
@Data
public class MascotaResponse {
    private Long id;
    private String nombre;
    private Long duenioId;
}
