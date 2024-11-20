package ar.edu.utn.frc.TPGrupo9.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Incidentes")
@Data
@JsonPropertyOrder({ "id", "prueba", "motivo" })
public class Incidente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PRUEBA")
    @JsonIgnore
    private Prueba prueba;

    @JsonProperty("prueba")
    public Integer getPruebaId() {
        return prueba != null ? prueba.getId() : null;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_POSICION")
    @JsonIgnore
    private Posicion posicion;

    @Column(name = "MOTIVO")
    private String motivo;
}
