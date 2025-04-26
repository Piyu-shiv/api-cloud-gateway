//package com.prodev.spring.cloud.gateway.cloudgateway.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.config.GlobalCorsProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.CorsWebFilter;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
//import reactor.netty.http.client.HttpClient;
//
//
//@Configuration
//public class CorsConfig
//        extends CorsConfiguration {
//    @Autowired
//    HttpClient httpClient;
//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public CorsWebFilter corsWebFilter(GlobalCorsProperties properties) {
//        CorsConfiguration corsConfig = properties.getCorsConfigurations().get("/**");
//        UrlBasedCorsConfigurationSource source =
//                new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfig);
//        System.out.println( httpClient+" heyy");
//        return new CorsWebFilter(source);
//    }
//}
