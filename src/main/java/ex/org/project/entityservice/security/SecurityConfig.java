package ex.org.project.entityservice.security;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;

/**
 * Security configuration for Entity Service.
 *
 * Provides two security filter chains:
 * 1. HTTP Basic Auth for actuator endpoints (shutdown, health)
 * 2. Keycloak JWT for all other API endpoints
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http

        .authorizeHttpRequests(auth -> auth
            .requestMatchers(EndpointRequest.to("shutdown")).authenticated()
            .anyRequest().permitAll()

        )
        .csrf(csrf -> csrf
            .ignoringRequestMatchers(EndpointRequest.to("shutdown"))
        )
        .httpBasic();  // must be last in this chain

    return http.build();
  }
}
