package ar.edu.utn.frc.TPGrupo9.Controller;

import ar.edu.utn.frc.TPGrupo9.DTO.PruebaRequest;
import ar.edu.utn.frc.TPGrupo9.DTO.ReporteKilometrosRequest;
import ar.edu.utn.frc.TPGrupo9.Models.Prueba;
import ar.edu.utn.frc.TPGrupo9.Services.PruebaService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/crear")
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

    @GetMapping("/generarReporteIncidentes")
    public ResponseEntity<String> generarReporteIncidentes() {
        try {
<<<<<<< HEAD
            String respuesta = service.generarReporteIncidentes();
            return ResponseEntity.ok(respuesta);
=======
            String mensaje = service.generarReporteIncidentes();
            return ResponseEntity.ok(mensaje);
>>>>>>> e03ea57e9a2768c5af4497457cffb455870cff03
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/generarReporteIncidentesXEmpleado/{idEmpleado}")
    public ResponseEntity<String> generarReporteIncidenteXEmpleado(@PathVariable int idEmpleado) {
        try {
<<<<<<< HEAD
            String respuesta = service.generarReporteIncidentesXEmpleado(idEmpleado);
            return ResponseEntity.ok(respuesta);
=======
            String mensaje = service.generarReporteIncidentesXEmpleado(idEmpleado);
            return ResponseEntity.ok(mensaje);
>>>>>>> e03ea57e9a2768c5af4497457cffb455870cff03
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/generarReportePruebasXVehiculo/{idVehiculo}")
    public ResponseEntity<String> generarReportePruebasXVehiculo(@PathVariable int idVehiculo) {
        try {
<<<<<<< HEAD
            String respuesta = service.generarReportePruebasXVehiculo(idVehiculo);
            return ResponseEntity.ok(respuesta);
=======
            String mensaje = service.generarReportePruebasXVehiculo(idVehiculo);
            return ResponseEntity.ok(mensaje);
>>>>>>> e03ea57e9a2768c5af4497457cffb455870cff03
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/generarReporteKilometros")
    public ResponseEntity<String> generarReporteKilometrosRecorridos(@RequestBody ReporteKilometrosRequest request){
        try {
            String mensaje = service.generarReporteKilometrosRecorridos(request.getIdVehiculo(),
                                                                        request.getFechaInicio(),
                                                                        request.getFechaFin());
            return ResponseEntity.ok(mensaje);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        /*
        {
            "idVehiculo": 1,
            "fechaInicio": "20/11/2024",
            "fechaFin": "25/11/2024"
         }
        */
    }
}
