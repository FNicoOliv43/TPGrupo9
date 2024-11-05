package ar.edu.utn.frc.TPGrupo9.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table (name = "Posiciones")
@Data
public class Posicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "ID")
    private int id;

    @JoinColumn(name = "FECHA_HORA")
    private String fecha_hora;

    @JoinColumn(name = "LATITUD")
    private int latitud;

    @JoinColumn(name = "LONGITUD")
    private int longitud;
}
