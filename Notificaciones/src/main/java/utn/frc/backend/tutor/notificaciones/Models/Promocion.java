package utn.frc.backend.tutor.notificaciones.Models;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table (name = "Promociones")
@Data
public class Promocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "TEXTO")
    private String texto;

}
