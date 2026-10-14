package com.patitas.turno.adapter.out.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Cliente HTTP declarativo hacia ms-veterinario.
 * name = "ms-veterinario" coincide con el spring.application.name de ese servicio en Eureka.
 */
@FeignClient(name = "ms-veterinario")
public interface VeterinarioFeignClient {

    @GetMapping(value = "/api/veterinarios/{id}", produces = "application/json")
    VeterinarioResponse findById(@PathVariable("id") Long id);
}
