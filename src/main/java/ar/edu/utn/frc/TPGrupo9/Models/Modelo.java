package ar.edu.utn.frc.TPGrupo9.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;


@Entity
@Table(name = "Modelos")
@Data
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @OneToMany(mappedBy = "modelo", fetch = FetchType.EAGER)
    @ToString.Exclude // Excluir de toString para evitar bucle
    private Set<Vehiculo> vehiculos;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_MARCA")
    @JsonIgnore
    private Marca marca;

    // hashCode y equals basados solo en 'id'
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Modelo other = (Modelo) obj;
        return id == other.id;
    }
}