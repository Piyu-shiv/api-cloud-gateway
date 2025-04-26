package com.prodev.spring.cloud.gateway.cloudgateway;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.netty.http.client.HttpClient;


@SpringBootApplication
public class CloudGatewayApplication {

    public static void main(String[] args) {
        System.out.println("coming here....main");
        SpringApplication.run(CloudGatewayApplication.class, args);
    }
//    @Bean
//    public HttpClient httpClient() {
//        return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
//    }
//}
}
