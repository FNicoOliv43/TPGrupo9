package ar.edu.utn.frc.TPGrupo9.DTO;

import lombok.Data;

@Data
public class PruebaRequest {
    private int vehiculoId;
    private int interesadoId;
    private int empleadoId;
    private String comentario;
}
