package com.vetSystem.Controller;

import com.vetSystem.DTO.TurnoRequesDTO;
import com.vetSystem.DTO.TurnoResponseDTO;
import com.vetSystem.Exception.ResourceNotFoundException;
import com.vetSystem.Repository.TurnoRepository;
import com.vetSystem.Service.TurnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// El error de compilacion que impedia levantar el proyecto NO estaba aca:
// este controller compila bien. El "cannot find symbol" venia de DuenioController
// (usaba un mapper que no estaba inyectado). Lo dejo anotado por si vuelve a confundir.
@RestController
@RequestMapping("/api/turnos")
@RequiredArgsConstructor
public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    //Completar todo el CRUD
    @PostMapping
    public ResponseEntity<?> guardarTurno(@RequestBody TurnoRequesDTO turnoRequesDTO){
        try{
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(turnoService.crearTurno(turnoRequesDTO));
            }catch(ResourceNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }catch(RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

}
