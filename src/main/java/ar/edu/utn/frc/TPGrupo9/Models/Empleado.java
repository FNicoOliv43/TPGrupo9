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
    @Column(name = "LEGAJO")
    private int legajo;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;


    @Column(name = "TELEFONO_CONTACTO")
    private int telefonoContacto;


    @OneToMany (mappedBy = "empleado", fetch = FetchType.EAGER)
    private Set<Prueba> pruebas;
}
