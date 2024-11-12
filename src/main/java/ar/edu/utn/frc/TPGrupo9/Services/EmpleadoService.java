package ar.edu.utn.frc.TPGrupo9.Services;

import ar.edu.utn.frc.TPGrupo9.Models.Empleado;
import ar.edu.utn.frc.TPGrupo9.Repository.EmpleadoRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {
    private final EmpleadoRepository repository;

    @Autowired
    public EmpleadoService(EmpleadoRepository repository) {
        this.repository = repository;
    }

    public Iterable<Empleado> getAll(){
        return repository.findAll();
    }

    public Empleado getByLegajo(int legajo) throws ServiceException {
        return repository.findById(legajo).orElseThrow(()->
                new ServiceException("Persona no encontrada"));
    }

}
