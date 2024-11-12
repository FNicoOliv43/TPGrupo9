package ar.edu.utn.frc.TPGrupo9.Controller;

import ar.edu.utn.frc.TPGrupo9.Models.Vehiculo;
import ar.edu.utn.frc.TPGrupo9.Services.VehiculoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {
    private final VehiculoService service;

    @Autowired
    public VehiculoController(VehiculoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Iterable<Vehiculo>> findAllVehiculos() {
        return ResponseEntity.ok(service.getAll());
    }

}
