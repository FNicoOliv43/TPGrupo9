package ar.edu.utn.frc.TPGrupo9.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;


@Entity
@Table(name = "Vehiculos")
@Data
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "ID")
    private int id;

    @JoinColumn(name = "PATENTE")
    private String patente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_MODELO")
    @ToString.Exclude // Evitar bucle en toString
    private Modelo modelo;

    @JoinColumn(name = "ANIO")
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