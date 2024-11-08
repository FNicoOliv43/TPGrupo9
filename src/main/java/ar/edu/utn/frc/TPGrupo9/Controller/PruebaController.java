package ar.edu.utn.frc.TPGrupo9.Controller;

import ar.edu.utn.frc.TPGrupo9.Models.Prueba;
import ar.edu.utn.frc.TPGrupo9.Services.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prueba")
public class PruebaController {
    private final PruebaService service;

    @Autowired
    public PruebaController(PruebaService service) {
        this.service = service;
    }

    @GetMapping("/all") // Añadimos el mapeo de la URL
    public List<Prueba> findAllPrueba() {
        return service.getAll();
    }
}
