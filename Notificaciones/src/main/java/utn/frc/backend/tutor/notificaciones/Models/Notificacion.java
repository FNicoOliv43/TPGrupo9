package utn.frc.backend.tutor.notificaciones.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Notificaciones")
@Data
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "TELEFONOS")
    private String telefonos;

    @Column(name = "MENSAJE")
    private String mensaje;
}
