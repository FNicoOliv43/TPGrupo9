package ar.edu.utn.frc.TPGrupo9.Repository;

import ar.edu.utn.frc.TPGrupo9.Models.Interesado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InteresadoRepository extends JpaRepository<Interesado, Integer> {

    List<Interesado> findAll();

    @Query("""
         SELECT CASE WHEN EXISTS (
                 SELECT 1 FROM Interesado i
                 WHERE i.id = :clienteId
                 AND i.restringido = false
                 AND i.fechaVencimientoLicencia > :fechaHoraAhora
         ) THEN true ELSE false END
     """)
    Boolean isInteresadoAptoParaPrueba(@Param("clienteId") int clienteId, @Param("fechaHoraAhora") LocalDateTime fechaHoraAhora);


}
