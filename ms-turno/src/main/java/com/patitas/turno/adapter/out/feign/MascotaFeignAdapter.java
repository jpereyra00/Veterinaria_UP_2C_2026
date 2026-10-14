package com.patitas.turno.adapter.out.feign;

import com.patitas.turno.domain.port.out.MascotaServicePort;
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
    public boolean existsById(Long id) {
        try {
            client.findById(id);
            return true;
        } catch (feign.FeignException.NotFound e) {
            return false;
        }
    }
}
