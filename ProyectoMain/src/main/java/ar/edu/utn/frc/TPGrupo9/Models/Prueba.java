package ar.edu.utn.frc.TPGrupo9.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table (name = "Pruebas")
@Data
@JsonPropertyOrder({ "id", "vehiculo", "interesado", "empleado", "fechaHoraInicio", "fechaHoraFin", "comentarios" })
public class Prueba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VEHICULO")
    @JsonIgnore
    private Vehiculo vehiculo;

    @JsonProperty("vehiculo")
    public Integer getVehiculoId() {
        return vehiculo != null ? vehiculo.getId() : null;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_INTERESADO")
    @JsonIgnore
    private Interesado interesado;

    @JsonProperty("interesado")
    public Integer getInteresadoId() {
        return interesado != null ? interesado.getId() : null;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_EMPLEADO")
    @JsonIgnore
    private Empleado empleado;

    @JsonProperty("empleado")
    public Integer getEmpleadoId() {
        return empleado != null ? empleado.getLegajo() : null;
    }


    @Column(name = "FECHA_HORA_INICIO")
    private LocalDateTime fechaHoraInicio;

    @Column(name = "FECHA_HORA_FIN")
    private LocalDateTime  fechaHoraFin;

    @Column(name = "COMENTARIOS")
    private String comentarios;

    @OneToMany (mappedBy = "prueba", fetch = FetchType.EAGER)
    private Set<Incidente> incidentes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prueba prueba = (Prueba) o;
        return id == prueba.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
