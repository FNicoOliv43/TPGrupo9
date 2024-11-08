package ar.edu.utn.frc.TPGrupo9.Services;

import ar.edu.utn.frc.TPGrupo9.Models.Prueba;
import ar.edu.utn.frc.TPGrupo9.Repository.PruebaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PruebaService {
private PruebaRepository pruebaRepository;

public List<Prueba> getAll(){
        return pruebaRepository.findAll();
    }
}
