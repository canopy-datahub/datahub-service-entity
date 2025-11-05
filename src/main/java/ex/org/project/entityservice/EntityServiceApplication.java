package ex.org.project.entityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(
    proxyBeanMethods = false,
    scanBasePackages = {
        "ex.org.project.entityservice",         // Entity service components (including mappers)
        "ex.org.project.datahub.auth"           // Keycloak library components  
    }
)
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {
    "ex.org.project.entityservice.repository",  // Entity service repositories
    "ex.org.project.datahub.auth.repository"    // Keycloak library repositories
})
@EntityScan(basePackages = {
    "ex.org.project.entityservice.model",       // Entity service entities
    "ex.org.project.datahub.auth.model"         // Keycloak library entities
})
public class EntityServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntityServiceApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
