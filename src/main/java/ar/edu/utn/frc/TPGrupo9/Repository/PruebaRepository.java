package ar.edu.utn.frc.TPGrupo9.Repository;

import ar.edu.utn.frc.TPGrupo9.Models.Prueba;
import ar.edu.utn.frc.TPGrupo9.Models.Interesado;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PruebaRepository extends JpaRepository<Prueba, Integer> {

    List<Prueba> findAll();

    @Query("SELECT p FROM Prueba p WHERE p.fechaHoraFin IS NULL")
    List<Prueba> findPruebasEnCurso();

    @Query("""
           SELECT CASE WHEN COUNT(p) = 0 THEN true ELSE false END 
           FROM Prueba p 
           WHERE p.vehiculo.id = :vehiculoId 
           AND p.fechaHoraFin IS NULL""")
    boolean isVehiculoDisponibleParaPrueba(@Param("vehiculoId") Long vehiculoId);


    @Query("""
           SELECT CASE WHEN COUNT(i) = 1 THEN true ELSE false END
           FROM Interesado i
           WHERE i.id = :clienteId
           AND i.restringido = false
           
           """)
    boolean isClienteAptoParaPrueba(@Param("clienteId") Long clienteId);

}

//AND i.fechaVencimientoLicencia > CURRENT_TIMESTAMP