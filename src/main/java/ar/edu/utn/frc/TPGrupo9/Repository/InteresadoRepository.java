package ar.edu.utn.frc.TPGrupo9.Repository;

import ar.edu.utn.frc.TPGrupo9.Models.Interesado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteresadoRepository extends JpaRepository<Interesado, Integer> {

    List<Interesado> findAll();

    @Query("""
        SELECT CASE WHEN COUNT(i) = 1 THEN true ELSE false END
        FROM Interesado i
        WHERE i.id = :clienteId
        AND i.restringido = false
    """)
    boolean isInteresadoAptoParaPrueba(@Param("clienteId") int clienteId);
    //AND DATETIME(i.fechaVencimientoLicencia) > CURRENT_TIMESTAMP
    // esta linea falta en Query, cuando funcione el mapeo de fechas

}
