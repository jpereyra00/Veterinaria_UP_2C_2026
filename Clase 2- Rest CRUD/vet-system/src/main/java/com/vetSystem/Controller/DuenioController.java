package com.vetSystem.Controller;

import com.vetSystem.Entity.Duenio;
import com.vetSystem.Service.DuenioService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/duenio")
public class DuenioController {

    @Autowired
    private DuenioService duenioService;

    @PostMapping
    public Duenio registrarDuenio(@RequestBody Duenio duenio){
       return duenioService.registrarDuenio(duenio);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Duenio>> buscarPorId(@PathVariable Long id){
        Optional<Duenio> duenioBuscado= duenioService.buscarPorId(id);
        if(duenioBuscado.isPresent()){
            return ResponseEntity.ok(duenioBuscado);
        }else {
            return ResponseEntity.notFound().build();
        }

    }

}
