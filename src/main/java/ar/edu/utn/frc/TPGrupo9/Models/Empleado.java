package Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Table (name = "Empleados")
public class Empleado {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "ID")
    private int legajo;
    @Getter
    @Setter
    @JoinColumn(name = "NOMBRE")
    private String nombre;
    @Getter
    @Setter
    @JoinColumn(name = "APELLIDO")
    private String apellido;
    @Getter
    @Setter
    @JoinColumn(name = "TELEFONO_CONTACTO")
    private int telefono_contacto;
    @Getter
    @Setter
    @OneToMany (mappedBy = "id_empleado", fetch = FetchType.EAGER)
    private Set<Prueba> pruebas;
}
