package utn.frc.backend.tutor.notificaciones.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.frc.backend.tutor.notificaciones.Models.Notificacion;

import java.util.List;

@Repository
public interface NotificacionRepo extends JpaRepository<Notificacion, Integer> {

    List<Notificacion> findAll();

}