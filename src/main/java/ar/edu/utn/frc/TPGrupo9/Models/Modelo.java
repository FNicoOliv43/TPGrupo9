package Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Table (name = "Modelos")
public class Modelo {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @Setter
    private String descripcion;
    @Getter
    @Setter
    @OneToMany (mappedBy = "modelo", fetch = FetchType.EAGER)
    private Set<Vehiculo> vehiculos;
    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Marca marca;
}
