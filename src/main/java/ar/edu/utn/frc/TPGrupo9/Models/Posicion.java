package Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table (name = "Posiciones")
public class Posicion {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "ID")
    private int id;
    @Getter
    @Setter
    @JoinColumn(name = "FECHA_HORA")
    private String fecha_hora;
    @Getter
    @Setter
    @JoinColumn(name = "LATITUD")
    private int latitud;
    @Getter
    @Setter
    @JoinColumn(name = "LONGITUD")
    private int longitud;
}
