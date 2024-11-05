package ar.edu.utn.frc.TPGrupo9.Services;

import ar.edu.utn.frc.TPGrupo9.Models.Vehiculo;
import ar.edu.utn.frc.TPGrupo9.Repository.VehiculoRepository; // Asegúrate de importar el repositorio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService  {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    public List<String> obtenerTodasLasPatentes() {
        return vehiculoRepository.findAllPatentes();
    }
}

