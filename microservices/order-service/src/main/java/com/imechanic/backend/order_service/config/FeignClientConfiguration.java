package com.imechanic.backend.order_service.config;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignClientConfiguration {

    private static final String AUTHORIZATION_HEADER = HttpHeaders.AUTHORIZATION;
    private static final String BEARER_PREFIX = "Bearer ";

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String token = getTokenJwt();
            if (token != null) {
                requestTemplate.header(AUTHORIZATION_HEADER, BEARER_PREFIX + token);
            }
        };
    }

    private String getTokenJwt() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            String jwtToken = requestAttributes.getRequest().getHeader(AUTHORIZATION_HEADER);
            if (jwtToken != null && jwtToken.startsWith(BEARER_PREFIX)) {
                return jwtToken.substring(BEARER_PREFIX.length());
            }
        }
        return null;
    }
}
