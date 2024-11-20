package ar.edu.utn.frc.TPGrupo9.Services;

import ar.edu.utn.frc.TPGrupo9.Models.*;
import ar.edu.utn.frc.TPGrupo9.Repository.*;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class PruebaService {
    private final PruebaRepository repository;
    private final InteresadoRepository interesadoRepository;
    private final VehiculoRepository vehiculoRepository;
    private final EmpleadoRepository empleadoRepository;
    private final PosicionRepository posicionRepository;
    private final IncidenteRepository incidenteRepository;
    private final NotificacionRepository notificacionRepository;

    @Autowired
    public PruebaService(PruebaRepository repository, InteresadoRepository interesadoRepository,
                         VehiculoRepository vehiculoRepository, EmpleadoRepository empleadoRepository,
                         PosicionRepository posicionRepository, IncidenteRepository incidenteRepository,
                         NotificacionRepository notificacionRepository) {
        this.repository = repository;
        this.interesadoRepository = interesadoRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.empleadoRepository = empleadoRepository;
        this.posicionRepository = posicionRepository;
        this.incidenteRepository = incidenteRepository;
        this.notificacionRepository = notificacionRepository;
    }

    public Iterable<Prueba> getAll(){
        return repository.findAll();
    }

    public Prueba getById(int id) throws ServiceException {
        return repository.findById(id).orElseThrow(() ->
                new ServiceException("Prueba no encontrada"));
    }

    //1.b
    public Iterable<Prueba> getPruebasEnCurso(){
        return repository.findPruebasEnCurso();
    }

    //1.a
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

    //1.c
    public Prueba finalizarPrueba(int id, String comentario) throws ServiceException {
        Prueba prueba = repository.findPruebaEnCursoById(id)
                .orElseThrow(() -> new ServiceException("Prueba no encontrada o ya finalizada"));

        prueba.setFechaHoraFin(LocalDateTime.now());
        prueba.setComentarios(comentario);

        return repository.save(prueba);
    }

    //1.d
    public String procesarPosicion(int vehiculoId, double lat, double lon) throws ServiceException {
        if (vehiculoRepository.isVehiculoDisponibleParaPrueba(vehiculoId)) {
            throw new RuntimeException("El vehículo no esta en prueba.");
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://labsys.frc.utn.edu.ar/apps-disponibilizadas/backend/api/v1/configuracion/";
        Agencia agencia = restTemplate.getForObject(url, Agencia.class);

        System.out.println(agencia);

        int idPrueba = vehiculoRepository.findPruebaActivaDeVehiculo(vehiculoId);
        Prueba prueba = repository.findById(idPrueba)
                .orElseThrow(() -> new RuntimeException("Prueba no encontrada"));
        Vehiculo vehiculo = vehiculoRepository.findById(vehiculoId)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
        Empleado empleado = empleadoRepository.findById(prueba.getEmpleadoId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        Interesado interesado = interesadoRepository.findById(prueba.getInteresadoId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Posicion posicion = new Posicion();
        posicion.setVehiculo(vehiculo);
        posicion.setFechaHora(LocalDateTime.now());
        posicion.setLatitud(lat);
        posicion.setLongitud(lon);
        posicionRepository.save(posicion);

        if(agencia.estaEnRadio(posicion.getLatitud(), posicion.getLongitud())){
            if (agencia.estaEnZonaRestringida(posicion.getLatitud(), posicion.getLongitud())) {
                //Creacion incidente
                Incidente incidente = new Incidente();
                incidente.setPrueba(prueba);
                incidente.setPosicion(posicion);
                incidente.setMotivo("El vehiculo " + vehiculoId + " ha entrado a una zona restringida");
                incidenteRepository.save(incidente);

                //Restringir intereado
                interesado.setRestringido(true);
                interesadoRepository.save(interesado);

                //Notificacion a empleado
                Notificacion notificacion = new Notificacion();
                notificacion.setTelefonos(empleado.getTelefonoContacto());
                notificacion.setMensaje("El vehiculo de la prueba que esta a tu cargo ha entrado a una zona restringida, " +
                        "volver inmediatamente");
                notificacionRepository.save(notificacion);

                return "El vehiculo " + vehiculoId + " esta ha entrado a una zona restringida, se ha notificado al empleado";
            }
        }else {
            //Creacion incidente
            Incidente incidente = new Incidente();
            incidente.setPrueba(prueba);
            incidente.setPosicion(posicion);
            incidente.setMotivo("El vehiculo " + vehiculoId + " ha entrado a una zona restringida");
            incidenteRepository.save(incidente);

            //Restringir intereado
            interesado.setRestringido(true);
            interesadoRepository.save(interesado);

            //Notificacion a empleado
            Notificacion notificacion = new Notificacion();
            notificacion.setTelefonos(empleado.getTelefonoContacto());
            notificacion.setMensaje("El vehiculo de la prueba que esta a tu cargo ha salido del radio permitido, " +
                    "volver inmediatamente");
            notificacionRepository.save(notificacion);

            return "El vehiculo " + vehiculoId + " esta ha salido del radio permitido, se ha notificado al empleado";

        }
        return "El vehiculo " + vehiculoId + " esta en una posicion valida";
    }

}

/*
POS EN ZONA RESTRINGIDA
{
    "vehiculoId": 1,
    "lat": 42.5090,
    "lon": 1.5370
}
POS FUERA DE RADIO
{
    "vehiculoId": 1,
    "lat": 42.6000,
    "lon": 1.6000
}
POS VALIDA
{
    "vehiculoId":1,
    "lat":42.488867,
    "lon":1.514714
}
 */