package com.example.junior_portal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Deprecated
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(false)
                .allowedMethods("PUT", "DELETE", "POST", "GET", "OPTIONS", "HEAD", "PATCH")
                .allowedHeaders("*")
                .allowedOrigins("*");
    }
}
