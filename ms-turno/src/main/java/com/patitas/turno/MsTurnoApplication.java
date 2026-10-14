package com.patitas.turno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Arranque de ms-turno.
 * @EnableFeignClients hace que Spring escanee las interfaces @FeignClient
 * (en adapter.out.feign) y genere su implementacion HTTP en tiempo de ejecucion.
 * Sin esta anotacion, MascotaFeignClient/VeterinarioFeignClient no se inyectan y el contexto no arranca.
 */
@SpringBootApplication
@EnableFeignClients
public class MsTurnoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsTurnoApplication.class, args);
    }
}
