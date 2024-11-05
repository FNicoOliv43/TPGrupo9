package Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Table (name = "Interesados")
public class Interesado {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "ID")
    private int id;
    @Getter
    @Setter
    @JoinColumn(name = "TIPO_DOCUMENTO")
    private String tipo_documento;
    @Getter
    @Setter
    @JoinColumn(name = "DOCUMENTO")
    private String documento;
    @Getter
    @Setter
    @JoinColumn(name = "NOMBRE")
    private String nombre;
    @Getter
    @Setter
    @JoinColumn(name = "APELLIDO")
    private String apellido;
    @Getter
    @Setter
    @JoinColumn(name = "RESTRINGIDO")
    private int restringido;
    @Getter
    @Setter
    @JoinColumn(name = "NRO_LICENCIA")
    private int nro_licencia;
    @Getter
    @Setter
    @JoinColumn(name = "FECHA_VENCIMIENTO")
    private String fecha_vencimiento_licencia;
    @Getter
    @Setter
    @OneToMany (mappedBy = "id_interesado", fetch = FetchType.EAGER)
    private Set<Prueba> pruebas;
}
