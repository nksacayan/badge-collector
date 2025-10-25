package com.nsac.badgecollectorserver.configs;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BadgeImageConfig implements WebMvcConfigurer {

    @Value("${badge.images.upload-dir:uploads/badges}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String resourceLocation = "file:" + Paths.get(uploadDir).toAbsolutePath().toString() + "/";
        registry.addResourceHandler("/api/badges/images/**")
                .addResourceLocations(resourceLocation)
                .setCachePeriod(3600);
    }
}
