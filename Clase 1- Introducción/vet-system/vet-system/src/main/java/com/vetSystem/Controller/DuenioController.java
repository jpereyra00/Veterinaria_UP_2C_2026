package com.vetSystem.Controller;

import com.vetSystem.Entity.Duenio;
import com.vetSystem.Service.DuenioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/duenio")
public class DuenioController {

    private DuenioService duenioService;

    public Duenio registrarDuenio(Duenio duenio){
       return duenioService.registrarDuenio(duenio);
    }
    public ResponseEntity<Optional<Duenio>> buscarPorId(Long id){
        return ResponseEntity.ok(duenioService.buscarPorId(id));
    }

}
