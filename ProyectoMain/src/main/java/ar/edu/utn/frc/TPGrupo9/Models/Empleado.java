package ar.edu.utn.frc.TPGrupo9.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;
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
    private String telefonoContacto;

    @OneToMany (mappedBy = "empleado", fetch = FetchType.EAGER)
    private Set<Prueba> pruebas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return legajo == empleado.legajo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(legajo);
    }
}
