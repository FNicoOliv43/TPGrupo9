package ar.edu.utn.frc.TPGrupo9.Repository;

import ar.edu.utn.frc.TPGrupo9.Models.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {

    List<Vehiculo> findAll();

    @Query("""
           SELECT CASE WHEN COUNT(p) = 0 THEN true ELSE false END 
           FROM Prueba p 
           WHERE p.vehiculo.id = :vehiculoId 
           AND p.fechaHoraFin IS NULL""")
    boolean isVehiculoDisponibleParaPrueba(@Param("vehiculoId") int vehiculoId);

    @Query("""
           SELECT p.id
           FROM Prueba p
           WHERE p.vehiculo.id = :vehiculoId
           AND p.fechaHoraFin IS NULL""")
    int findPruebaActivaDeVehiculo(@Param("vehiculoId") int vehiculoId);

}