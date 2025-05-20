package com.prodev.spring.cloud.gateway.cloudgateway.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
public class LoggingGlobalPreFilter implements GlobalFilter {

    final Logger logger =
            LoggerFactory.getLogger(LoggingGlobalPreFilter.class);

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            GatewayFilterChain chain) {
        System.out.println(" coming with request....");
        ServerHttpRequest req=exchange.getRequest();

        if(req.getMethod()== HttpMethod.GET && !req.getHeaders().containsKey("Authorization")){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            // Optionally, write an error message to the response body
            // exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap("Error: Missing header".getBytes())));
            exchange.getResponse().setComplete();
        }
        logger.info("Global Pre Filter executed");
        return chain.filter(exchange);
    }
}