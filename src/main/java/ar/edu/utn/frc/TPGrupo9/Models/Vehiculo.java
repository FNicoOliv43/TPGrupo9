package Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Table (name = "Vehiculos")
public class Vehiculo {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "ID")
    private int id;
    @Getter
    @Setter
    @JoinColumn(name = "PATENTE")
    private String patente;
    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_MODELO")
    @JsonIgnore
    private Modelo modelo;
    @Getter
    @Setter
    @OneToMany (mappedBy = "vehiculo", fetch = FetchType.EAGER)
    private Set<Prueba> pruebas;
}
