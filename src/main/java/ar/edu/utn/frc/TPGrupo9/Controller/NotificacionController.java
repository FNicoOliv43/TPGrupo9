package ar.edu.utn.frc.TPGrupo9.Controller;

import ar.edu.utn.frc.TPGrupo9.DTO.NotificacionRequest;
import ar.edu.utn.frc.TPGrupo9.DTO.PruebaRequest;
import ar.edu.utn.frc.TPGrupo9.Models.Notificacion;
import ar.edu.utn.frc.TPGrupo9.Models.Prueba;
import ar.edu.utn.frc.TPGrupo9.Services.NotificacionService;
import ar.edu.utn.frc.TPGrupo9.Services.PruebaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/notificacion")
public class NotificacionController {
    private final NotificacionService service;

    @Autowired
    public NotificacionController(NotificacionService service) {
        this.service = service;
    }

    @PostMapping("/promocion")
    public ResponseEntity<Notificacion> createNotificacion(@RequestBody NotificacionRequest request) {
        return ResponseEntity.ok(service.notificarPromocion(request.getTelefonos(), request.getPromocionId()));
    }
}
