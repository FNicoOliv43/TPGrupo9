package ar.edu.utn.frc.TPGrupo9.Controller;

import ar.edu.utn.frc.TPGrupo9.Models.Interesado;
import ar.edu.utn.frc.TPGrupo9.Services.IntereadoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/interesados")
public class InteresadoController {
    private final IntereadoService service;

    @Autowired
    public InteresadoController(IntereadoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Iterable<Interesado>> findAllInteresados() {
        return ResponseEntity.ok(service.getAll());
    }

}
