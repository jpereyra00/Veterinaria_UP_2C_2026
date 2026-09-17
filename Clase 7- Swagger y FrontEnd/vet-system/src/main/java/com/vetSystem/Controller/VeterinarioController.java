package com.vetSystem.Controller;

import com.vetSystem.DTO.VeterinarioDTO;
import com.vetSystem.Service.VeterinarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veterinario")
public class VeterinarioController {

    @Autowired
    private VeterinarioService veterinarioService;

    @GetMapping
    public ResponseEntity<List<VeterinarioDTO>> listarTodos() {
        return ResponseEntity.ok(veterinarioService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeterinarioDTO> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(veterinarioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<VeterinarioDTO> registrarVeterinario(@Valid @RequestBody VeterinarioDTO veterinarioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(veterinarioService.registrarVeterinario(veterinarioDTO));
    }
}
