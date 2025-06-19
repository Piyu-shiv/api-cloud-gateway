//package com.prodev.spring.cloud.gateway.cloudgateway.component;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//@Component
//public class LoggingGlobalPostFilter {
//    final Logger logger =
//            LoggerFactory.getLogger(
//                    LoggingGlobalPostFilter.class);
//
//    @Bean
//    public GlobalFilter postGlobalFilter() {
//        return (exchange, chain) -> {
//            System.out.println("hey this is pre filter");
//            logger.info("hey this is pre filter  from logger");
//            return chain.filter(exchange)
//                    .then(Mono.fromRunnable(() -> {
//                        ServerHttpRequest req = exchange.getRequest();
//                        logger.info("Global Post Filter executed");
//                    }));
//        };
//    }
//}
//
