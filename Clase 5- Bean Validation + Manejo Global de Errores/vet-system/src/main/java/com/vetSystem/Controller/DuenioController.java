package com.vetSystem.Controller;

import com.vetSystem.DTO.DuenioDTO;
import com.vetSystem.Entity.Duenio;
import com.vetSystem.Exception.ResourceNotFoundException;
import com.vetSystem.Mapper.DuenioMapper;
import com.vetSystem.Service.DuenioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/duenio")
public class DuenioController {

    @Autowired
    private DuenioService duenioService;

    // Faltaba inyectar el mapper: el metodo listarTodo() lo usaba pero nunca se habia declarado,
    // por eso el compilador tiraba "cannot find symbol: duenioMapper".
    @Autowired
    private DuenioMapper duenioMapper;

    //debemos refactorizar para devolver DTO
    @PostMapping
    public ResponseEntity<?> registrarDuenio(@Valid @RequestBody DuenioDTO duenioDTO){
        DuenioDTO nuevo= duenioService.registrarDuenio(duenioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DuenioDTO> buscarPorId(@PathVariable ("id") Long id){
    return ResponseEntity.ok(duenioService.buscarPorId(id));
    }
}
