package ar.edu.utn.frc.TPGrupo9.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "Vehiculos")
@Data
@JsonPropertyOrder({ "id", "patente", "modelo", "anio"})
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "PATENTE")
    private String patente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_MODELO")
    @ToString.Exclude // Evitar bucle en toString
    @JsonIgnore
    private Modelo modelo;

    @JsonProperty("modelo")
    public Integer getEmpleadoId() {
        return modelo != null ? modelo.getId() : null;
    }

    @Column(name = "ANIO")
    private int anio;

    // hashCode y equals basados solo en 'id'
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vehiculo other = (Vehiculo) obj;
        return id == other.id;
    }
}