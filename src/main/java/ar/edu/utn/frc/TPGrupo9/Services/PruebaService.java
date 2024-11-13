package ar.edu.utn.frc.TPGrupo9.Services;

import ar.edu.utn.frc.TPGrupo9.Models.Empleado;
import ar.edu.utn.frc.TPGrupo9.Models.Interesado;
import ar.edu.utn.frc.TPGrupo9.Models.Prueba;
import ar.edu.utn.frc.TPGrupo9.Models.Vehiculo;
import ar.edu.utn.frc.TPGrupo9.Repository.EmpleadoRepository;
import ar.edu.utn.frc.TPGrupo9.Repository.InteresadoRepository;
import ar.edu.utn.frc.TPGrupo9.Repository.PruebaRepository;
import ar.edu.utn.frc.TPGrupo9.Repository.VehiculoRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class PruebaService {
    private final PruebaRepository repository;
    private final InteresadoRepository interesadoRepository;
    private final VehiculoRepository vehiculoRepository;
    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public PruebaService(PruebaRepository repository, InteresadoRepository interesadoRepository,
                         VehiculoRepository vehiculoRepository, EmpleadoRepository empleadoRepository) {
        this.repository = repository;
        this.interesadoRepository = interesadoRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.empleadoRepository = empleadoRepository;
    }

    public Iterable<Prueba> getAll(){
        return repository.findAll();
    }

    public Prueba getById(int id) throws ServiceException {
        return repository.findById(id).orElseThrow(() ->
                new ServiceException("Prueba no encontrada"));
    }

    public Iterable<Prueba> getPruebasEnCurso(){
        return repository.findPruebasEnCurso();
    }

    public Prueba createPrueba(int vehiculoId, int interesadoId, int empleadoId) {

        if (!vehiculoRepository.isVehiculoDisponibleParaPrueba(vehiculoId)) {
            throw new RuntimeException("El vehículo ya está siendo probado en este momento.");
        }

        LocalDateTime ahora = LocalDateTime.now();
        System.out.println(ahora);
        System.out.println(interesadoRepository.isInteresadoAptoParaPrueba(interesadoId, ahora));
        if (!interesadoRepository.isInteresadoAptoParaPrueba(interesadoId, ahora)) {
            throw new RuntimeException("El interesado no cumple con los requisitos para realizar una prueba.");
        }

        Vehiculo vehiculo = vehiculoRepository.findById(vehiculoId)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
        Interesado interesado = interesadoRepository.findById(interesadoId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        Prueba prueba = new Prueba();
        prueba.setInteresado(interesado);
        prueba.setVehiculo(vehiculo);
        prueba.setEmpleado(empleado);
        prueba.setFechaHoraInicio(LocalDateTime.now());

        return repository.save(prueba);
    }

    public Prueba finalizarPrueba(int id, String comentario) throws ServiceException {
        Prueba prueba = repository.findPruebaEnCursoById(id)
                .orElseThrow(() -> new ServiceException("Prueba no encontrada o ya finalizada"));

        prueba.setFechaHoraFin(LocalDateTime.now());
        prueba.setComentarios(comentario);

        return repository.save(prueba);
    }
}
