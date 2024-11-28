package ar.edu.utn.frc.TPGrupo9.Models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Data
public class Agencia {
    @Getter @Setter
    private Coordenadas coordenadasAgencia;
    @Getter @Setter
    private double radioAdmitidoKm;
    @Getter @Setter
    private List<ZonaRestringida> zonasRestringidas;

    public Agencia(){

    }

    public boolean estaEnRadio(double latVehiculo, double lonVehiculo){
        double distancia = calcularDistancia(latVehiculo, lonVehiculo, coordenadasAgencia.getLat(), coordenadasAgencia.getLon());

        return distancia <= radioAdmitidoKm;
    }

    private double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        double radioTierra = 6371.0; // Radio de la Tierra en kilómetros

        // Convertir latitudes y longitudes de grados a radianes
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return radioTierra * c; // Distancia en kilómetros
    }

    public boolean estaEnZonaRestringida(double lat, double lon){
        return zonasRestringidas.stream().anyMatch(zonaRestringida -> zonaRestringida.contiene(lat, lon));
    }

    public double getLatitud(){
        return coordenadasAgencia.getLat();
    }

    public double getLongitud(){
        return coordenadasAgencia.getLon();
    }


}
