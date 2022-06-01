package com.example.gatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

// custom filter ref : https://www.baeldung.com/spring-cloud-custom-gateway-filters
@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {
    public CustomFilter() {
        super(Config.class);
    }

    public static class Config {
        // put the configuration info
    }

    @Override
    public GatewayFilter apply(Config config) {
        // Custom Pre Filter
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            log.info("Custom Pre Filter: request id -> {}", request.getId()); // {} 컬리브레이션 내부에 알아서 바인딩된다
            return chain.filter(exchange) // post filter 추가
                .then(Mono.fromRunnable(() -> log.info("Custom Post Filter: response code -> {}", response.getStatusCode())));
        };
    }
}
