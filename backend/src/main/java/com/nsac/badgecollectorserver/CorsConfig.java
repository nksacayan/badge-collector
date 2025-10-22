package com.nsac.badgecollectorserver;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000", "http://127.0.0.1:3000")
            .allowedOriginPatterns("http://192.168.*.*", "http://10.*.*.*", "http://172.*.*.*")
            .allowedMethods("*")
            .allowCredentials(true);
    }
}