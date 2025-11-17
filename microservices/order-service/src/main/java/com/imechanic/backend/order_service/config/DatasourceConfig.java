package com.imechanic.backend.order_service.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatasourceConfig {
    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @PostConstruct
    public void printUrl() {
        System.out.println("🔧 Using DB: " + datasourceUrl);
    }

}
