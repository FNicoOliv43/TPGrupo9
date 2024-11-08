package ar.edu.utn.frc.TPGrupo9.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Table (name = "Empleados")
@Data
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "ID")
    private int legajo;

    @JoinColumn(name = "NOMBRE")
    private String nombre;

    @JoinColumn(name = "APELLIDO")
    private String apellido;


    @JoinColumn(name = "TELEFONO_CONTACTO")
    private int telefono_contacto;


    @OneToMany (mappedBy = "empleado", fetch = FetchType.EAGER)
    private Set<Prueba> pruebas;
}
