package ar.edu.utn.frc.TPGrupo9.Services;

import ar.edu.utn.frc.TPGrupo9.Models.Notificacion;
import ar.edu.utn.frc.TPGrupo9.Models.Promocion;
import ar.edu.utn.frc.TPGrupo9.Repository.NotificacionRepository;
import ar.edu.utn.frc.TPGrupo9.Repository.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionService {
    private final NotificacionRepository repository;
    private final PromocionRepository promocionRepository;

    @Autowired
    public NotificacionService(NotificacionRepository repository, PromocionRepository promocionRepository) {
        this.repository = repository;
        this.promocionRepository = promocionRepository;
    }

    public Iterable<Notificacion> getAll(){
        return repository.findAll();
    }

    public Notificacion notificarPromocion(List<String> telefonos, int promocionId){

        Promocion promocion = promocionRepository.findById(promocionId)
                .orElseThrow(() -> new RuntimeException("Promocion no encontrada"));

        Notificacion notificacion = new Notificacion();
        StringBuilder telefonosString = new StringBuilder();
        for (String telefono : telefonos){
            if (telefonosString.isEmpty()){
                telefonosString.append(telefono);
            }
            else{
                telefonosString.append(", ").append(telefono);
            }

        }
        notificacion.setTelefonos(telefonosString.toString());
        notificacion.setMensaje(promocion.getTexto());

        return repository.save(notificacion);
    }

}