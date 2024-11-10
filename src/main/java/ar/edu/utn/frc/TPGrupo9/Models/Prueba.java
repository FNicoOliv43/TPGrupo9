package ar.edu.utn.frc.TPGrupo9.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name = "Pruebas")
@Data
public class Prueba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "ID")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VEHICULO")
    @JsonIgnore
    private Vehiculo vehiculo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_interesado")
    @JsonIgnore
    private Interesado interesado;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_EMPLEADO")
    @JsonIgnore
    private Empleado empleado;

    @JoinColumn(name = "FECHA_HORA_INICIO")
    private String fecha_hora_inicio;

    @JoinColumn(name = "FECHA_HORA_FIN")
    private String fecha_hora_fin;

    @JoinColumn(name = "COMENTARIOS")
    private String comentarios;


}
