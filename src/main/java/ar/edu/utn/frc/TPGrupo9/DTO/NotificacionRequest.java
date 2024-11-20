package ar.edu.utn.frc.TPGrupo9.DTO;

import lombok.Data;

import java.util.List;

@Data
public class NotificacionRequest {
    private int promocionId;
    List<String> telefonos;
}
