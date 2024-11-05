package ar.edu.utn.frc.TPGrupo9.Controllers;

import ar.edu.utn.frc.TPGrupo9.Services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VehiculoController {
    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("/vehiculos/patentes")
    public List<String> obtenerTodasLasPatentes() {
        return vehiculoService.obtenerTodasLasPatentes();
    }
}
