package com.vetSystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuracion de CORS.
 *
 * El frontend (carpeta frontend/) se sirve desde otro origen (Live Server en
 * localhost:5500) mientras que la API corre en localhost:8080. Sin esta config
 * el navegador bloquea las llamadas fetch por politica de CORS y la tabla de
 * duenios queda vacia.
 *
 * Queda habilitado para toda la API (/api/**) para que, cuando los alumnos
 * agreguen los endpoints de Veterinario, Mascota y los metodos faltantes de
 * Duenio (PUT/DELETE), tambien funcionen desde el frontend sin tocar nada aca.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(
                        "http://localhost:5500",
                        "http://127.0.0.1:5500"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}
