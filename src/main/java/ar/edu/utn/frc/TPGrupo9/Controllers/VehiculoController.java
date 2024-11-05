package ar.edu.utn.frc.TPGrupo9.Controllers;

import ar.edu.utn.frc.TPGrupo9.Models.Vehiculo;
import Services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {
    private final VehiculoService service;
    @Autowired
    public VehiculoController(VehiculoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Vehiculo> getAllBodegas() {
        var v = service.getAll();
        v.forEach(System.out::println);
        return v;
    }
}
