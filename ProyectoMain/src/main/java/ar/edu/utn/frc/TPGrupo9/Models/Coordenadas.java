package ar.edu.utn.frc.TPGrupo9.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

public class Coordenadas {
    @Getter @Setter
    private double lat;
    @Getter @Setter
    private double lon;

    public Coordenadas(){

    }



}
