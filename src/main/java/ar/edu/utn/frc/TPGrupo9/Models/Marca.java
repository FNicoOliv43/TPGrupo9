package Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Table (name = "Marcas")
public class Marca {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "ID")
    private int id;
    @Getter
    @Setter
    @JoinColumn(name = "NOMBRE")
    private String nombre;
    @Getter
    @Setter
    @OneToMany (mappedBy = "marca", fetch = FetchType.EAGER)
    private Set<Modelo> modelos;
}
