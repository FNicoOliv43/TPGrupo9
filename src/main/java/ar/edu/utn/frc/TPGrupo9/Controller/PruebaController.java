package ar.edu.utn.frc.TPGrupo9.Controller;

import ar.edu.utn.frc.TPGrupo9.DTO.PruebaRequest;
import ar.edu.utn.frc.TPGrupo9.Models.Prueba;
import ar.edu.utn.frc.TPGrupo9.Services.PruebaService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/pruebas")
public class PruebaController {
    private final PruebaService service;

    @Autowired
    public PruebaController(PruebaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Iterable<Prueba>> findAllPrueba() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/enCurso")
    public ResponseEntity<Iterable<Prueba>> findPruebasEnCurso() {
        return ResponseEntity.ok(service.getPruebasEnCurso());
    }

    @PostMapping
    public ResponseEntity<Prueba> createPrueba(@RequestBody PruebaRequest request) {
        return ResponseEntity.ok(service.createPrueba(request.getVehiculoId(), request.getInteresadoId(), request.getEmpleadoId()));
    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity<Prueba> updatePersona(@RequestBody PruebaRequest request, @PathVariable int id) {
        try {
            Prueba prueba = service.finalizarPrueba(id, request.getComentario());
            return ResponseEntity.ok(prueba);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/verificarPosicion")
    public ResponseEntity<String> procesarPosicion(@RequestBody PruebaRequest request) {
        try {
            String mensaje = service.procesarPosicion(request.getVehiculoId(), request.getLat(), request.getLon()
            );
            return ResponseEntity.ok(mensaje);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
