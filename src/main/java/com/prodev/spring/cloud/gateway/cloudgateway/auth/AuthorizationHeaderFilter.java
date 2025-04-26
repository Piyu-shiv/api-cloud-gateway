package com.prodev.spring.cloud.gateway.cloudgateway.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.JwtSigner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {
    @Autowired
    private Environment env;


    public static class Config {

    }

    public AuthorizationHeaderFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        // TODO Auto-generated method stub
        System.out.println("heyyyy from gateway route...........");
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "NO AUTHORIZATION TOKEN ", HttpStatus.UNAUTHORIZED);
            }
            String authHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0).replace("Bearer", "");
            if(!isJwtValid(authHeader)){
                return onError(exchange,"JWT token is not Valid",HttpStatus.UNAUTHORIZED);
            }
            return chain.filter(exchange);
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String noAuthorization, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

//    private Claims getAllClaimsFromToken(String token) {
//        return Jwts.parser().setSigningKey(env.getProperty("token.secret")).parseClaimsJws(token).getBody();
//    }

    private boolean isJwtValid(String str) {
        boolean isValid = true;
        byte[] secretKeyBytes = Base64.getEncoder().encode(env.getProperty("token.secret").getBytes());
        SecretKey key = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS512.getJcaName());
        Jwt<Header, Claims> parsedToken = null;
        String subject=null;
        try {
            parsedToken = Jwts.parser().setSigningKey(key).parseClaimsJwt(str);
             subject = parsedToken.getBody().getSubject();
             if(subject==null){
                 isValid=false;
             }
        } catch (Exception e) {
            isValid=false;

        }
        return isValid;
    }
}
