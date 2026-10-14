package com.patitas.veterinario.adapter.in.web;

import com.patitas.veterinario.adapter.in.web.dto.VeterinarioMapper;
import com.patitas.veterinario.adapter.in.web.dto.VeterinarioRequest;
import com.patitas.veterinario.adapter.in.web.dto.VeterinarioResponse;
import com.patitas.veterinario.domain.model.Veterinario;
import com.patitas.veterinario.domain.port.in.VeterinarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Adaptador de ENTRADA: traduce HTTP -> llamadas al dominio.
 * El endpoint GET /api/veterinarios/{id} es el que ms-turno consume via Feign.
 */
@RestController
@RequestMapping("/api/veterinarios")
@RequiredArgsConstructor
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    @GetMapping
    public ResponseEntity<List<VeterinarioResponse>> listarTodos() {
        List<VeterinarioResponse> respuesta = veterinarioService.listarTodos().stream()
                .map(VeterinarioMapper::toResponse)
                .toList();
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeterinarioResponse> buscarPorId(@PathVariable Long id) {
        Veterinario veterinario = veterinarioService.buscarPorId(id);
        return ResponseEntity.ok(VeterinarioMapper.toResponse(veterinario));
    }

    @PostMapping
    public ResponseEntity<VeterinarioResponse> registrar(@Valid @RequestBody VeterinarioRequest request) {
        Veterinario creado = veterinarioService.registrar(VeterinarioMapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(VeterinarioMapper.toResponse(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeterinarioResponse> actualizar(@PathVariable Long id,
                                                          @Valid @RequestBody VeterinarioRequest request) {
        Veterinario actualizado = veterinarioService.actualizar(id, VeterinarioMapper.toEntity(request));
        return ResponseEntity.ok(VeterinarioMapper.toResponse(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable Long id) {
        veterinarioService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
