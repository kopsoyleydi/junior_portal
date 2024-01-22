package com.example.junior_portal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")// Указываете путь, для которого применяется CORS
                .allowedOrigins("http://localhost:3000")  // Разрешенные домены
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Разрешенные HTTP-методы
                .allowedHeaders("*");  // Разрешенные заголовки
    }
}
