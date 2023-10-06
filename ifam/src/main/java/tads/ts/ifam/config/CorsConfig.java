package tads.ts.ifam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permitir CORS em todas as URLs
                .allowedOrigins("*") // Permitir solicitações de todas as origens
                .allowedMethods("GET", "POST", "PUT", "DELETE"); // Permitir métodos HTTP específicos
    }
}
