package ar.edu.utn.frc.TPGrupo9.Repository;
import ar.edu.utn.frc.TPGrupo9.Models.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository ;

import java.util.List;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer > {
    List<Vehiculo> findByPatente(String patente);
    @Query("SELECT v.patente FROM Vehiculo v")
        List<String> findAllPatentes() ;
}
