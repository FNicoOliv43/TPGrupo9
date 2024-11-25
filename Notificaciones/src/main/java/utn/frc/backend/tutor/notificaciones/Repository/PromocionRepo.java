package utn.frc.backend.tutor.notificaciones.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.frc.backend.tutor.notificaciones.Models.Promocion;

import java.util.List;

@Repository
public interface PromocionRepo extends JpaRepository<Promocion, Integer> {

    List<Promocion> findAll();

}
