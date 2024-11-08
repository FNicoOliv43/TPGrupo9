package ar.edu.utn.frc.TPGrupo9.Repository;

import ar.edu.utn.frc.TPGrupo9.Models.Prueba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PruebaRepository extends JpaRepository<Prueba, Integer> {

   // @Query("SELECT p FROM Prueba p WHERE :fechaHora BETWEEN p.fechaHoraInicio AND p.fechaHoraFin")
   // List<Prueba> findPruebasEnCurso(@Param("fechaHora") LocalDateTime fechaHora);
    List<Prueba> findAll();

}
