package ar.edu.utn.frc.TPGrupo9.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;


@Entity
@Table (name = "Posiciones")
@Data
@JsonPropertyOrder({ "id", "vehiculo", "fechaHora", "latitud", "longitud"})
public class Posicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VEHICULO")
    @ToString.Exclude
    @JsonIgnore
    private Vehiculo vehiculo;

    @JsonProperty("vehiculo")
    public Integer getVehiculoId() {
        return vehiculo != null ? vehiculo.getId() : null;
    }

    @Column(name = "FECHA_HORA")
    private LocalDateTime fechaHora;

    @Column(name = "LATITUD")
    private double latitud;

    @Column(name = "LONGITUD")
    private double longitud;

    @Transient
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_INCIDENTE")
    @ToString.Exclude
    @JsonIgnore
    private Incidente incidente;

    public double getKilometrosRecorridos(double latAgencia, double lonAgencia){
        double R = 6371.0;

        latAgencia = Math.toRadians(latAgencia);
        lonAgencia = Math.toRadians(lonAgencia);
        double latRad = Math.toRadians(this.latitud);
        double lonRad = Math.toRadians(this.longitud);

        double dlat = latAgencia - latRad;
        double dlon = lonAgencia - lonRad;

        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(latAgencia) * Math.cos(latRad) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }

}
