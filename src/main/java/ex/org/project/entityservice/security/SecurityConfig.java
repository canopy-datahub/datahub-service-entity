package ex.org.project.entityservice.security;

import ex.org.project.datahub.auth.config.KeycloakSecurityConfig;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

/**
 * Security configuration for Entity Service.
 * Extends the shared Keycloak authentication library configuration.
 */
@Configuration
public class SecurityConfig extends KeycloakSecurityConfig {

  @Override
  protected void configureEndpointSecurity(
      AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth
  ) throws Exception {
    auth
        // Public endpoints (if any - adjust as needed)
        // .requestMatchers("/studies/public/**", "/search/public/**").permitAll()
        
        // Actuator endpoints
        .requestMatchers(EndpointRequest.to("health")).permitAll()
        .requestMatchers(EndpointRequest.to("shutdown")).authenticated()
        
        // All other endpoints require authentication
        .anyRequest().authenticated();
  }
}

