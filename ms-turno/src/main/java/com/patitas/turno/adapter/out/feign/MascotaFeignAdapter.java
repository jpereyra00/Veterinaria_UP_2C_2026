package com.patitas.turno.adapter.out.feign;

import com.patitas.turno.domain.exception.ServicioNoDisponibleException;
import com.patitas.turno.domain.port.out.MascotaServicePort;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Adaptador de SALIDA: implementa el puerto del dominio (MascotaServicePort)
 * usando el cliente Feign. Traduce el HTTP 404 del servicio remoto en un booleano limpio.
 * El dominio solo ve el puerto; esta clase es el "cable" hacia ms-mascota.
 */
@Component
@RequiredArgsConstructor
public class MascotaFeignAdapter implements MascotaServicePort {

    private final MascotaFeignClient client;

    @Override
    @CircuitBreaker(name = "ms-mascota", fallbackMethod = "existsByIdFallback")
    public boolean existsById(Long id) {
        try {
            client.findById(id);
            return true;
        } catch (feign.FeignException.NotFound e) {
            return false;
        }
    }
    public boolean existsByIdFallback(Long id, Throwable causa) {
        throw new ServicioNoDisponibleException("ms-mascota");
    }
}
