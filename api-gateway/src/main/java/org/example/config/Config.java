package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.servlet.config.annotation.*;


import java.util.Arrays;

@Configuration
public class Config implements WebMvcConfigurer {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public CorsWebFilter corsWebFilter(CorsConfigurationSource corsConfigurationSource) {
        return new CorsWebFilter(corsConfigurationSource);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("*");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/");
    }

   /* @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .maxAge(3600)
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("Origin", "Access-Control-Allow-Origin", "Content-Type", "Accept",
                        "Authorization", "Origin, Accept", "X-Requested-With",
                        "Access-Control-Request-Method", "Access-Control-Request-Headers")
                .exposedHeaders("Origin", "Total-Count", "Content-Type", "Accept", "Authorization",
                        "Access-Control-Allow-Origin", "Access-Control-Allow-Origin",
                        "Access-Control-Allow-Credentials")
                .allowCredentials(true);
    }*/

}
