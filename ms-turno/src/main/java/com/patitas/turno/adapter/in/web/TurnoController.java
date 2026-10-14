package com.patitas.turno.adapter.in.web;

import com.patitas.turno.adapter.in.web.dto.TurnoMapper;
import com.patitas.turno.adapter.in.web.dto.TurnoRequest;
import com.patitas.turno.adapter.in.web.dto.TurnoResponse;
import com.patitas.turno.domain.model.Turno;
import com.patitas.turno.domain.port.in.TurnoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Adaptador de ENTRADA: traduce HTTP -> llamadas al dominio.
 * POST /api/turnos dispara la validacion cruzada (mascota + veterinario) via Feign.
 */
@RestController
@RequestMapping("/api/turnos")
@RequiredArgsConstructor
public class TurnoController {

    private final TurnoService turnoService;

    @GetMapping
    public ResponseEntity<List<TurnoResponse>> listarTodos() {
        List<TurnoResponse> respuesta = turnoService.listarTodos().stream()
                .map(TurnoMapper::toResponse)
                .toList();
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoResponse> buscarPorId(@PathVariable Long id) {
        Turno turno = turnoService.buscarPorId(id);
        return ResponseEntity.ok(TurnoMapper.toResponse(turno));
    }

    @PostMapping
    public ResponseEntity<TurnoResponse> registrar(@Valid @RequestBody TurnoRequest request) {
        Turno creado = turnoService.registrar(TurnoMapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(TurnoMapper.toResponse(creado));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<TurnoResponse> cancelar(@PathVariable Long id) {
        Turno cancelado = turnoService.cancelar(id);
        return ResponseEntity.ok(TurnoMapper.toResponse(cancelado));
    }
}
