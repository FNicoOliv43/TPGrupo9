package utn.frc.backend.tutor.notificaciones.Controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.frc.backend.tutor.notificaciones.DTO.NotificacionIncidenteDTO;
import utn.frc.backend.tutor.notificaciones.DTO.NotificacionRequest;
import utn.frc.backend.tutor.notificaciones.Models.Notificacion;
import utn.frc.backend.tutor.notificaciones.Service.NotificacionService;

@Slf4j
@RestController
@RequestMapping("/notificacion")
public class NotificacionController {
    private final NotificacionService service;
    private final NotificacionService notificacionService;

    @Autowired
    public NotificacionController(NotificacionService service, NotificacionService notificacionService) {
        this.service = service;
        this.notificacionService = notificacionService;
    }

    @PostMapping("/promocion")
    public ResponseEntity<Notificacion> notificarPromocion(@RequestBody NotificacionRequest request) {
        return ResponseEntity.ok(service.notificarPromocion(request.getTelefonos(), request.getPromocionId()));
    }

    @PostMapping("/incidente")
    public ResponseEntity<String> notificarIncidente(@RequestBody NotificacionIncidenteDTO request) {
        notificacionService.notificarIncidente(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Notificación creada con éxito");
    }
}
