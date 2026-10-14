package adapter.in.web;

import adapter.ErrorResponse;
import adapter.in.web.dto.DuenioMapper;
import adapter.in.web.dto.DuenioRequest;
import adapter.in.web.dto.DuenioResponse;
import domain.model.Duenio;
import domain.port.in.DuenioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name="Duenios", description = "CRUD- clinica Veterinaria-Duenio")
@RestController
@RequestMapping("/api/duenio")
public class DuenioController {

    @Autowired
    private DuenioService duenioService;

     @Operation(
            summary = "Registrar un nuevo duenio",
            description = "crea un nuevo duenio. Todos los valores deben estar completos ya que: la cedula debe  estar comprendida entre los 7 y 8 caracteres"
    )
    @ApiResponses({
    @ApiResponse(responseCode = "201", description = "duenio creado exitosamente"),
    @ApiResponse(responseCode = "400", description = "Los datos son invalidos(falla de verificacion)",
    content=@Content(schema = @Schema(implementation = ErrorResponse.class))),
    @ApiResponse(responseCode = "409",description = "correo electronico ya registrado")})
    //capturar la excepcion donde sea necesario
    @PostMapping
    public ResponseEntity<DuenioResponse> registrarDuenio(@Valid @RequestBody DuenioRequest duenioRequest){
        Duenio duenio=  duenioService.registrar(DuenioMapper.toEntity(duenioRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(DuenioMapper.toResponse(duenio));
    }

    @Operation(
            summary = "buscar duenio por ID",
            description = "devuelve los datos del duenio con el ID indicado; sino existe devuelve 404")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "duenio encontrado"),
            @ApiResponse(responseCode = "404",description = "no existe un duenio con ese ID", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/{id}")
    public ResponseEntity<DuenioResponse> buscarPorId(@Parameter(description = "ID del duenio a buscar: ",example = "1") @PathVariable ("id") Long id){
         Duenio duenio = duenioService.buscarPorId(id);
         return ResponseEntity.ok(DuenioMapper.toResponse(duenio));
    }
    @Operation(
            summary = "Listar todos los duenios que viven en la clinica",
            description = "Devuelve la lista completa de los duenios registrados, si no hay duenios devuelve una lista vacia con estado 200")

    @GetMapping
    public ResponseEntity<List<DuenioResponse>> buscarTodos(){
         List<DuenioResponse> respuesta= duenioService.listarTodos().stream().map(DuenioMapper::toResponse).toList();
        return ResponseEntity.ok(respuesta);
        }
}
