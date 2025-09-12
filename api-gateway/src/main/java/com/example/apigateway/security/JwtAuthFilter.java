package com.example.apigateway.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.util.Base64;

@Component
public class JwtAuthFilter implements WebFilter {

    private final SecretKey secretKey;
    private static final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);

    public JwtAuthFilter(@Value("${jwt.secret}") String base64Secret) {
        this.secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(base64Secret));
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getPath().toString();
        HttpMethod method = exchange.getRequest().getMethod();

        log.info("Incoming request: {} {}", method, path);
        log.info("Request headers: {}", exchange.getRequest().getHeaders());

        if (path.startsWith("/api/auth/") || method == HttpMethod.OPTIONS) {
            log.info("Skipping auth check for path {} and method {}", path, method);
            return chain.filter(exchange);
        }

        String token = null;
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            log.info("Found token in Authorization header: {}", token);
        }

        if (token == null && exchange.getRequest().getCookies().containsKey("accessToken")) {
            token = exchange.getRequest().getCookies().getFirst("accessToken").getValue();
            log.info("Found token in cookie: {}", token);
        }

        if (token == null) {
            log.warn("No token found. Returning UNAUTHORIZED");
            exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            log.info("JWT token is valid");
        } catch (Exception e) {
            log.warn("JWT validation failed: {}", e.getMessage());
            exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }
}
