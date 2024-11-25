package utn.frc.backend.tutor.apigateway.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class GWConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder,
                               @Value("${microservicio-main}") String uriMain,
                               @Value("${microservicio-notificaciones}") String uriNotificaciones) {
        return builder.routes()
                .route(p -> p.path("/interesados").uri(uriMain))
                .route(p -> p.path("/pruebas").uri(uriMain))
                .route(p -> p.path("/pruebas/enCurso").uri(uriMain))
                .route(p -> p.path("/pruebas/crear").uri(uriMain))
                .route(p -> p.path("/pruebas/finalizar/{id}").uri(uriMain))
                .route(p -> p.path("/pruebas/verificarPosicion").uri(uriMain))
                .route(p -> p.path("/pruebas/generarReporteIncidentes").uri(uriMain))
                .route(p -> p.path("/pruebas/generarReporteIncidentesXEmpleado/{idEmpleado}").uri(uriMain))
                .route(p -> p.path("/pruebas/generarReportePruebasXVehiculo/{idVehiculo}").uri(uriMain))
                .route(p -> p.path("/vehiculos").uri(uriMain))
                .route(p -> p.path("/notificacion/promocion").uri(uriNotificaciones))
                .build();
    }
}