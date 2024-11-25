package ar.edu.utn.frc.TPGrupo9.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Data

public class ZonaRestringida {
    @Getter @Setter
    private Coordenadas noroeste;
    @Getter @Setter
    private Coordenadas sureste;

    public ZonaRestringida(){

    }

    public boolean contiene(double lat, double lon) {
        return lat <= noroeste.getLat() && lat >= sureste.getLat() &&
                lon >= noroeste.getLon() && lon <= sureste.getLon();
    }


}
