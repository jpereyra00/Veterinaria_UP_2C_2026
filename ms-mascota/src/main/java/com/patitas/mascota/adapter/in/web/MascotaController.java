package com.patitas.mascota.adapter.in.web;

import com.patitas.mascota.adapter.in.web.dto.MascotaMapper;
import com.patitas.mascota.adapter.in.web.dto.MascotaRequest;
import com.patitas.mascota.adapter.in.web.dto.MascotaResponse;
import com.patitas.mascota.domain.model.Mascota;
import com.patitas.mascota.domain.port.in.MascotaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Adaptador de ENTRADA (driving adapter): traduce HTTP -> llamadas al dominio.
 * Depende de la interface MascotaService (puerto de entrada), NUNCA de MascotaServiceImpl.
 * El endpoint GET /api/mascotas/{id} es el que ms-turno consume via Feign en el Sprint 11.
 */
@RestController
@RequestMapping("/api/mascotas")
@RequiredArgsConstructor
public class MascotaController {

    private final MascotaService mascotaService;

    @GetMapping
    public ResponseEntity<List<MascotaResponse>> listarTodos() {
        List<MascotaResponse> respuesta = mascotaService.listarTodos().stream()
                .map(MascotaMapper::toResponse)
                .toList();
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaResponse> buscarPorId(@PathVariable Long id) {
        Mascota mascota = mascotaService.buscarPorId(id);
        return ResponseEntity.ok(MascotaMapper.toResponse(mascota));
    }

    @PostMapping
    public ResponseEntity<MascotaResponse> registrar(@Valid @RequestBody MascotaRequest request) {
        Mascota creada = mascotaService.registrar(MascotaMapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(MascotaMapper.toResponse(creada));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MascotaResponse> actualizar(@PathVariable Long id,
                                                      @Valid @RequestBody MascotaRequest request) {
        Mascota actualizada = mascotaService.actualizar(id, MascotaMapper.toEntity(request));
        return ResponseEntity.ok(MascotaMapper.toResponse(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable Long id) {
        mascotaService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
