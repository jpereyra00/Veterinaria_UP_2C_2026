package com.patitas.mascota.adapter.in.web.dto;

import com.patitas.mascota.domain.model.Mascota;

/**
 * Traductor entre DTOs (mundo HTTP) y la entidad de dominio (mundo interno).
 * Mapeo manual a proposito. Vive en el adaptador de entrada: el dominio no conoce estos DTOs.
 */
public final class MascotaMapper {

    private MascotaMapper() {
        // clase de utilidades: no se instancia
    }

    /** DTO de entrada -> entidad de dominio (sin id: lo asigna la BD). */
    public static Mascota toEntity(MascotaRequest req) {
        Mascota mascota = new Mascota();
        mascota.setNombre(req.getNombre());
        mascota.setEspecie(req.getEspecie());
        mascota.setRaza(req.getRaza());
        mascota.setFechaNacimiento(req.getFechaNacimiento());
        mascota.setDuenioId(req.getDuenioId());
        return mascota;
    }

    /** Entidad de dominio -> DTO de salida. */
    public static MascotaResponse toResponse(Mascota mascota) {
        return new MascotaResponse(
                mascota.getId(),
                mascota.getNombre(),
                mascota.getEspecie(),
                mascota.getRaza(),
                mascota.getFechaNacimiento(),
                mascota.getDuenioId()
        );
    }
}
