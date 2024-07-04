package com.imechanic.backend.api_gateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/api/auth/signup",
            "/api/auth/login",
            "/api/auth/confirmation"
    );

    public Predicate<ServerHttpRequest> isSecured =
            serverHttpRequest -> openApiEndpoints
                    .parallelStream().noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
}
