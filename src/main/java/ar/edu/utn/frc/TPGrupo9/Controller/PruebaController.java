package ar.edu.utn.frc.TPGrupo9.Controller;

import ar.edu.utn.frc.TPGrupo9.Models.Prueba;
import ar.edu.utn.frc.TPGrupo9.Services.PruebaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Iterable<Prueba>>  findAllPrueba() {
        return ResponseEntity.ok(service.getAll());
    }
}
