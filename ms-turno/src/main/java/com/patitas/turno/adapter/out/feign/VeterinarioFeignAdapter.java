package com.patitas.turno.adapter.out.feign;

import com.patitas.turno.domain.exception.ServicioNoDisponibleException;
import com.patitas.turno.domain.port.out.VeterinarioServicePort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Adaptador de SALIDA: implementa VeterinarioServicePort usando el cliente Feign.
 * Convierte el 404 remoto en false; cualquier otro fallo Feign se propaga.
 */
@Component
@RequiredArgsConstructor
public class VeterinarioFeignAdapter implements VeterinarioServicePort {

    private final VeterinarioFeignClient client;

    @Override
    @CircuitBreaker(name = "ms-veterinario", fallbackMethod = "existsByIdFallback")
    public boolean existsById(Long id) {
        try {
            client.findById(id);
            return true;
        } catch (feign.FeignException.NotFound e) {
            return false;
        }
    }

    public boolean existsByIdFallback(Long id, Throwable causa) {
        throw new ServicioNoDisponibleException("ms-veterinario");
    }
}
