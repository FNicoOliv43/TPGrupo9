package ar.edu.utn.frc.TPGrupo9.Repository;

import ar.edu.utn.frc.TPGrupo9.Models.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromocionRepository extends JpaRepository<Promocion, Integer> {

    List<Promocion> findAll();

}