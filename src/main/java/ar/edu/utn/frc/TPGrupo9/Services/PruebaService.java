package ar.edu.utn.frc.TPGrupo9.Services;

import ar.edu.utn.frc.TPGrupo9.Models.Interesado;
import ar.edu.utn.frc.TPGrupo9.Models.Prueba;
import ar.edu.utn.frc.TPGrupo9.Models.Vehiculo;
import ar.edu.utn.frc.TPGrupo9.Repository.InteresadoRepository;
import ar.edu.utn.frc.TPGrupo9.Repository.PruebaRepository;
import ar.edu.utn.frc.TPGrupo9.Repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PruebaService {
    private final PruebaRepository repository;
    private final InteresadoRepository interesadoRepository;
    private final VehiculoRepository vehiculoRepository;

    @Autowired
    public PruebaService(PruebaRepository repository, InteresadoRepository interesadoRepository, VehiculoRepository vehiculoRepository) {
        this.repository = repository;
        this.interesadoRepository = interesadoRepository;
        this.vehiculoRepository = vehiculoRepository;
    }


    public Iterable<Prueba> getAll(){
            return repository.findAll();
        }

    public Iterable<Prueba> getPruebasEnCurso(){
        return repository.findPruebasEnCurso();
    }

    public Prueba createPrueba(int interesadoId, int vehiculoId) {
        if (!interesadoRepository.isInteresadoAptoParaPrueba(interesadoId)) {
            throw new RuntimeException("El interesado no cumple con los requisitos para realizar una prueba.");
        }

        if (!vehiculoRepository.isVehiculoDisponibleParaPrueba(vehiculoId)) {
            throw new RuntimeException("El vehículo ya está siendo probado en este momento.");
        }

        Interesado interesado = interesadoRepository.findById(interesadoId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Vehiculo vehiculo = vehiculoRepository.findById(vehiculoId)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        Prueba prueba = new Prueba();
        prueba.setInteresado(interesado);
        prueba.setVehiculo(vehiculo);
        //prueba.setEmpleado(empleado);
        LocalDateTime fechaHoraInicio = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHoraString = fechaHoraInicio.format(formatter);
        prueba.setFechaHoraInicio(fechaHoraString);

        return repository.save(prueba);
    }
}
