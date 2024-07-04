package com.imechanic.backend.order_service.config;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imechanic.backend.order_service.config.service.IUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper() {
        JsonFactory jsonFactory = new JsonFactory();
        jsonFactory.setStreamReadConstraints(
                jsonFactory.streamReadConstraints().rebuild().maxNestingDepth(2000).build()
        );
        return new ObjectMapper(jsonFactory);
    }
}
