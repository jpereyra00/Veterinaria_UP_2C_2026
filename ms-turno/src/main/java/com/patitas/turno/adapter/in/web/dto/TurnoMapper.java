package com.patitas.turno.adapter.in.web.dto;

import com.patitas.turno.domain.model.Turno;

/**
 * Traductor entre DTOs (mundo HTTP) y la entidad de dominio (mundo interno).
 * El estado NO se mapea desde el request: lo asigna el dominio (todo turno nace PENDIENTE).
 */
public final class TurnoMapper {

    private TurnoMapper() {
        // clase de utilidades: no se instancia
    }

    /** DTO de entrada -> entidad de dominio (sin id ni estado: los asigna el sistema). */
    public static Turno toEntity(TurnoRequest req) {
        Turno turno = new Turno();
        turno.setFechaHora(req.getFechaHora());
        turno.setMascotaId(req.getMascotaId());
        turno.setVeterinarioId(req.getVeterinarioId());
        turno.setMotivo(req.getMotivo());
        return turno;
    }

    /** Entidad de dominio -> DTO de salida. */
    public static TurnoResponse toResponse(Turno turno) {
        return new TurnoResponse(
                turno.getId(),
                turno.getFechaHora(),
                turno.getMascotaId(),
                turno.getVeterinarioId(),
                turno.getMotivo(),
                turno.getEstado()
        );
    }
}
