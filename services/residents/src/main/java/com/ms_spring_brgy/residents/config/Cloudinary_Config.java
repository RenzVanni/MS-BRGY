package com.ms_spring_brgy.residents.config;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
public class Cloudinary_Config {

    private final Environment env;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(env.getProperty("cloudinary_url"));
    }
}
