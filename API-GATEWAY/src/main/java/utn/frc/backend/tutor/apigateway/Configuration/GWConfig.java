package utn.frc.backend.tutor.apigateway.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;

@Configuration
@EnableWebFluxSecurity
public class GWConfig {

    private static final Logger logger = LoggerFactory.getLogger(GWConfig.class);

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

    @Bean
    public JwtDecoder jwtDecoder() {
        return JwtDecoders.fromIssuerLocation("https://labsys.frc.utn.edu.ar/aim/realms/backend-tps");
    }

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(authorize -> authorize
                        .pathMatchers(HttpMethod.GET, "/pruebas").hasAuthority("ADMIN")
                        .pathMatchers("/pruebas/crear", "/notificacion/promocion").hasAuthority("EMPLEADO")
                        .pathMatchers("/pruebas/verificarPosicion").hasAuthority("VEHICULO")
                        .pathMatchers("/pruebas/generarReporteIncidentes").hasAuthority("ADMIN")
                        .pathMatchers("/pruebas/generarReporteIncidentesXEmpleado/{idEmpleado}").hasAuthority("ADMIN")
                        .pathMatchers("/pruebas/generarReportePruebasXVehiculo/{idVehiculo}").hasAuthority("ADMIN")
                        .anyExchange().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer ->
                        jwtConfigurer.jwtAuthenticationConverter(reactiveJwtAuthenticationConverter())))
                .csrf(ServerHttpSecurity.CsrfSpec::disable);

        return http.build();
    }

    @Bean
    public Converter<Jwt, Mono<AbstractAuthenticationToken>> reactiveJwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

        grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
        grantedAuthoritiesConverter.setAuthorityPrefix("");

        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}


