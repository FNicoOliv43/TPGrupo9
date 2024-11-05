package Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table (name = "Pruebas")
public class Prueba {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "ID")
    private int id;
    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VEHICULO")
    @JsonIgnore
    private Vehiculo vehiculo;
    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_INTERESADO")
    @JsonIgnore
    private Interesado interesado;
    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_EMPLEADO")
    @JsonIgnore
    private Empleado empleado;
    @Getter
    @Setter
    @JoinColumn(name = "FECHA_HORA_INICIO")
    private String fecha_hora_inicio;
    @Getter
    @Setter
    @JoinColumn(name = "FECHA_HORA_FIN")
    private String fecha_hora_fin;
    @Getter
    @Setter
    @JoinColumn(name = "COMENTARIOS")
    private String comentarios;


}
