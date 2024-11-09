package ar.edu.utn.frc.TPGrupo9.Services;

import ar.edu.utn.frc.TPGrupo9.Models.Prueba;
import ar.edu.utn.frc.TPGrupo9.Repository.PruebaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PruebaService {
    private final PruebaRepository repository;

    @Autowired
    public PruebaService(PruebaRepository repository) {
        this.repository = repository;
    }

    public Iterable<Prueba> getAll(){
            return repository.findAll();
        }
}
