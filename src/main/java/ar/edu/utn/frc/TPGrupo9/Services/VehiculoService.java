package ar.edu.utn.frc.TPGrupo9.Services;

import ar.edu.utn.frc.TPGrupo9.Models.Vehiculo;
import ar.edu.utn.frc.TPGrupo9.Repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VehiculoService {
    private final VehiculoRepository repository;

    @Autowired
    public VehiculoService(VehiculoRepository repository) { this.repository = repository; }

    public Iterable<Vehiculo> getAll(){
        return repository.findAll();
    }
}
