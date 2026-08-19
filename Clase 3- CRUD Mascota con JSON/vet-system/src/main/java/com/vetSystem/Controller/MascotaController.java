package com.vetSystem.Controller;

import com.vetSystem.Entity.Mascota;
import com.vetSystem.Service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascota")
public class MascotaController {
    @Autowired
    private MascotaService mascotaService;

    @GetMapping
    public ResponseEntity<List<Mascota>> buscarTodos() {
        return  ResponseEntity.ok(mascotaService.listarEntidades());
    }
    @PostMapping
    public ResponseEntity<Mascota> registrarMascota(@RequestBody Mascota mascota) {
        return ResponseEntity.ok(mascotaService.registrarEntidad(mascota));
    }
}
