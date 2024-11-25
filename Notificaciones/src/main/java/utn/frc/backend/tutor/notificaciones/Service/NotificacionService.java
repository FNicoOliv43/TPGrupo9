package utn.frc.backend.tutor.notificaciones.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frc.backend.tutor.notificaciones.DTO.NotificacionIncidenteDTO;
import utn.frc.backend.tutor.notificaciones.DTO.NotificacionRequest;
import utn.frc.backend.tutor.notificaciones.Models.Notificacion;
import utn.frc.backend.tutor.notificaciones.Models.Promocion;
import utn.frc.backend.tutor.notificaciones.Repository.NotificacionRepo;
import utn.frc.backend.tutor.notificaciones.Repository.PromocionRepo;

import java.util.List;

@Service
public class NotificacionService {
    private final NotificacionRepo repository;
    private final PromocionRepo promocionRepository;

    @Autowired
    public NotificacionService(NotificacionRepo repository, PromocionRepo promocionRepository) {
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

    public void notificarIncidente(NotificacionIncidenteDTO request){
        Notificacion notificacion = new Notificacion();
        notificacion.setTelefonos(request.getTelefonos());
        notificacion.setMensaje(request.getMensaje());
        repository.save(notificacion);
    }

}