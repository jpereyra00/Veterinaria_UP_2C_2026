package com.patitas.turno.domain.port.out;

/**
 * Puerto de SALIDA del dominio: "necesito saber si un veterinario existe".
 * El dominio depende de esta interface, NO del VeterinarioFeignClient.
 */
public interface VeterinarioServicePort {
    boolean existsById(Long id);
}
