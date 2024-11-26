package utn.frc.backend.tutor.apigateway.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class GWSecurityConfig {

    // Definir el bean JwtDecoder
    @Bean
    public JwtDecoder jwtDecoder() {
        // Configurar JwtDecoder usando la URL de discovery de Keycloak
        return JwtDecoders.fromIssuerLocation("https://labsys.frc.utn.edu.ar/aim/realms/backend-tps");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/pruebas/crear", "/notificacion/promocion").hasRole("EMPLEADO")
                        .requestMatchers("/pruebas/verificarPosicion").hasRole("VEHICULO")
                        .requestMatchers("/pruebas/generarReporteIncidentes").hasRole("ADMIN")
                        .requestMatchers("/pruebas/generarReporteIncidentesXEmpleado/{idEmpleado}").hasRole("ADMIN")
                        .requestMatchers("/pruebas/generarReportePruebasXVehiculo/{idVehiculo}").hasRole("ADMIN")
                        .requestMatchers("/pruebas/").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));  // Autenticación basada en JWT
        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        var jwtAuthenticationConverter = new JwtAuthenticationConverter();
        var grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }
}
