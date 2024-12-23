package ar.edu.utn.frc.TPGrupo9.Repository;

import ar.edu.utn.frc.TPGrupo9.Models.Prueba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PruebaRepository extends JpaRepository<Prueba, Integer> {

    List<Prueba> findAll();

    @Query("SELECT p FROM Prueba p WHERE p.fechaHoraFin IS NULL")
    List<Prueba> findPruebasEnCurso();

    @Query("SELECT p FROM Prueba p WHERE p.id = :id AND p.fechaHoraFin IS NULL")
    Optional<Prueba> findPruebaEnCursoById(@Param("id") int id);

}

