package utn.frc.backend.tutor.notificaciones.DTO;

import lombok.Data;

import java.util.List;

@Data
public class NotificacionRequest {
    private int promocionId;
    List<String> telefonos;
}
