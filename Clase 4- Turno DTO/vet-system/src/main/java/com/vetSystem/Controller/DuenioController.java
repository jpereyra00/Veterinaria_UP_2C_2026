package com.vetSystem.Controller;

import com.vetSystem.DTO.DuenioDTO;
import com.vetSystem.Entity.Duenio;
import com.vetSystem.Mapper.DuenioMapper;
import com.vetSystem.Service.DuenioService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Duenio registrarDuenio(@RequestBody Duenio duenio){
       return duenioService.registrarEntidad(duenio);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Duenio>> buscarPorId(@PathVariable ("id") Long id){
        Optional<Duenio> duenioBuscado= duenioService.buscarPorId(id);
        if(duenioBuscado.isPresent()){
            return ResponseEntity.ok(duenioBuscado);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/nombre/")
    public ResponseEntity<Duenio> buscarPorNombre(@RequestParam String nombre){
        Optional<Duenio> duenioBuscado= duenioService.buscarPorString(nombre);
        if(duenioBuscado.isPresent()){
            return ResponseEntity.ok(duenioBuscado.get());
        }else  {
            return ResponseEntity.notFound().build();
        }
    }
    //quiero ver todos los duenios que tenemos
    // Ademas de agregar el mapper, corregi el tipo de retorno: devolvemos una LISTA de DTOs,
    // asi que tiene que ser List<DuenioDTO> y no un solo DuenioDTO. Le sumo el @GetMapping
    // que faltaba para que el endpoint sea accesible.
    @GetMapping
    public ResponseEntity<List<DuenioDTO>> listarTodo(){
        return ResponseEntity.ok(duenioService.listarEntidades()
                .stream()
                .map(duenioMapper::toDto)
                .collect(Collectors.toList()));
    }
}
