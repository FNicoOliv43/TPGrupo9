package ar.edu.utn.frc.TPGrupo9.Services;


import ar.edu.utn.frc.TPGrupo9.Models.Vehiculo;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService {

    public List<Vehiculo> getAll(){
        try(var emf = Persistence.createEntityManagerFactory("persistence");
            var em = emf.createEntityManager();){
            em.getTransaction().begin();
            var vehiculos = em.createQuery("select v from Vehiculo v", Vehiculo.class).getResultList();
            em.getTransaction().commit();

            return vehiculos;

        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }
}
