package com.patitas.mascota;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Arranque de ms-mascota.
 * A diferencia de ms-duenio, acá todo cuelga de com.patitas.mascota, así que el
 * escaneo por defecto de Spring Boot encuentra entidades, repos y servicios solo.
 */
@SpringBootApplication
public class MsMascotaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsMascotaApplication.class, args);
    }
}
