package ar.edu.utn.frc.TPGrupo9.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Entity
@Table (name = "Posiciones")
@Data
public class Posicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_HORA")
    private String fechaHora;

    @Column(name = "LATITUD")
    private int latitud;

    @Column(name = "LONGITUD")
    private int longitud;
}
