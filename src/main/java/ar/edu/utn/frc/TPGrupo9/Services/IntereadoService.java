package ar.edu.utn.frc.TPGrupo9.Services;

import ar.edu.utn.frc.TPGrupo9.Models.Interesado;
import ar.edu.utn.frc.TPGrupo9.Repository.InteresadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntereadoService {
    private final InteresadoRepository repository;

    @Autowired
    public IntereadoService(InteresadoRepository repository) {
        this.repository = repository;
    }

    public Iterable<Interesado> getAll(){
        return repository.findAll();
    }

}
