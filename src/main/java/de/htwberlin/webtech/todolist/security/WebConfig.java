package de.htwberlin.webtech.todolist.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{

@Override
public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/v*/**")
        .allowedMethods("*")
        .allowedOrigins(
                "http://localhost:8080",
            "http://localhost:8081",
            "https://webtech-git-frontend.herokuapp.com/"
            );
}

}
