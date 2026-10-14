package com.patitas.turno.domain.port.out;

/**
 * Puerto de SALIDA del dominio: "necesito saber si una mascota existe".
 * El dominio depende de esta interface, NO del MascotaFeignClient (que es un adaptador).
 * Asi el dominio no sabe nada de HTTP ni de Feign: solo pide un booleano.
 */
public interface MascotaServicePort {
    boolean existsById(Long id);
}
