package ar.edu.utn.frc.TPGrupo9.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;


@Entity
@Table (name = "Marcas")
@Data
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NOMBRE")
    private String nombre;

    @OneToMany (mappedBy = "marca", fetch = FetchType.EAGER)
    private Set<Modelo> modelos;
}
