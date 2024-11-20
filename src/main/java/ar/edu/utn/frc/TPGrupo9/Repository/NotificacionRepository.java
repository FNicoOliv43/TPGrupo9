package ar.edu.utn.frc.TPGrupo9.Repository;

import ar.edu.utn.frc.TPGrupo9.Models.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {

    List<Notificacion> findAll();

}