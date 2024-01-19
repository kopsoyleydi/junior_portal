package com.example.junior_portal.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CorsFilterConfig extends CorsFilter {

    public CorsFilterConfig() {
        super(corsConfigurationSource());
    }


    private static CorsConfigurationSource corsConfigurationSource() {

        Map<String, CorsConfiguration> corsConfigurationMap = new HashMap<>();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        List<String> allowedOrigins = new ArrayList<>();
        allowedOrigins.add("*");
        corsConfiguration.setAllowedOrigins(allowedOrigins);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfigurationMap.put("*", corsConfiguration);

        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                return corsConfiguration;
            }
        };
    }

}
