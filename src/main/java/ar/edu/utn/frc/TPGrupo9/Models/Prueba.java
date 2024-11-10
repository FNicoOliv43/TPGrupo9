package ar.edu.utn.frc.TPGrupo9.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table (name = "Pruebas")
@Data
public class Prueba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VEHICULO")
    @JsonIgnore
    private Vehiculo vehiculo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_INTERESADO")
    @JsonIgnore
    private Interesado interesado;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_EMPLEADO")
    @JsonIgnore
    private Empleado empleado;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_HORA_INICIO")
    private String fechaHoraInicio;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_HORA_FIN")
    private String  fechaHoraFin;

    @Column(name = "COMENTARIOS")
    private String comentarios;


}
