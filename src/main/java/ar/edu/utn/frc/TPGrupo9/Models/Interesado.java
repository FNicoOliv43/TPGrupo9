package ar.edu.utn.frc.TPGrupo9.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table (name = "Interesados")
@Data
public class Interesado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "TIPO_DOCUMENTO")
    private String tipo_documento;

    @Column(name = "DOCUMENTO")
    private String documento;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "RESTRINGIDO")
    private boolean restringido;

    @Column(name = "NRO_LICENCIA")
    private int nroLicencia;

    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //@CreationTimestamp
    //@Temporal(TemporalType.TIMESTAMP)
    //Probar opciones de arriba, pero no me funciono ninguna
    @Column(name = "FECHA_VENCIMIENTO_LICENCIA")
    private String fechaVencimientoLicencia;

    @JsonIgnore
    @OneToMany (mappedBy = "interesado", fetch = FetchType.EAGER)
    private Set<Prueba> pruebas;
}
