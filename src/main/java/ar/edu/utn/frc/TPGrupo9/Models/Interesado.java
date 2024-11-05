package ar.edu.utn.frc.TPGrupo9.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Table (name = "Interesados")
@Data
public class Interesado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "ID")
    private int id;

    @JoinColumn(name = "TIPO_DOCUMENTO")
    private String tipo_documento;

    @JoinColumn(name = "DOCUMENTO")
    private String documento;

    @JoinColumn(name = "NOMBRE")
    private String nombre;

    @JoinColumn(name = "APELLIDO")
    private String apellido;

    @JoinColumn(name = "RESTRINGIDO")
    private int restringido;

    @JoinColumn(name = "NRO_LICENCIA")
    private int nro_licencia;

    @JoinColumn(name = "FECHA_VENCIMIENTO")
    private String fecha_vencimiento_licencia;

    @OneToMany (mappedBy = "id_interesado", fetch = FetchType.EAGER)
    private Set<Prueba> pruebas;
}
