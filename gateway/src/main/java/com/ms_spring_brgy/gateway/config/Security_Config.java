package com.ms_spring_brgy.gateway.config;

import com.ms_spring_brgy.gateway.enums.Paths;
import com.ms_spring_brgy.gateway.enums.Routes;
import com.ms_spring_brgy.gateway.enums.URI;
import com.ms_spring_brgy.gateway.service.ClearExpiredCookie;
import com.ms_spring_brgy.gateway.service.CookieConverter;
import com.ms_spring_brgy.gateway.service.JwtAuthConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class Security_Config {
    private final JwtAuthConverter jwtAuthConverter;
    private final ClearExpiredCookie cLearExpiredCookie;

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http)  {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(customizer -> customizer
                        .pathMatchers("/api/v1/accounts/login").permitAll()
//                        .pathMatchers("/api/v1/accounts/**").authenticated()
                        .anyExchange().authenticated()
                );

        http
                .oauth2ResourceServer(customizer -> customizer
                        .bearerTokenConverter(new CookieConverter())
                        .jwt(jwtConfigurer -> jwtConfigurer
                                .jwtAuthenticationConverter(new ReactiveJwtAuthenticationConverterAdapter(jwtAuthConverter)))
                        .authenticationEntryPoint(cLearExpiredCookie));


        return http.build();
    }

//    @Bean
//    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> reactiveJwtAuthConverter(JwtAuthConverter jwtAuthConverter) {
//        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthConverter);
//    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(Routes.ACCOUNT.getLabel(), r -> r
                        .path(Paths.ACCOUNT.getLabel())
                        .uri(URI.ACCOUNT.getLabel())
                )
                .route(Routes.RESIDENTS.getLabel(), r -> r
                        .path(Paths.RESIDENTS.getLabel())
                        .uri(URI.RESIDENTS.getLabel())
                )
                .route(Routes.OFFICIALS.getLabel(), r -> r
                        .path(Paths.OFFICIALS.getLabel())
                        .uri(URI.OFFICIALS.getLabel())
                )
                .route(Routes.BLOTTER.getLabel(), r -> r
                        .path(Paths.BLOTTER.getLabel())
                        .uri(URI.BLOTTER.getLabel())
                )
                .route(Routes.COMPLAINT.getLabel(), r -> r
                        .path(Paths.COMPLAINT.getLabel())
                        .uri(URI.COMPLAINT.getLabel())
                )
                .route(Routes.DAE.getLabel(), r -> r
                        .path(Paths.DAE.getLabel())
                        .uri(URI.DAE.getLabel())
                )
                .route(Routes.HAS.getLabel(), r -> r
                        .path(Paths.HAS.getLabel())
                        .uri(URI.HAS.getLabel())
                )
                .route(Routes.INCIDENT.getLabel(), r -> r
                        .path(Paths.INCIDENT.getLabel())
                        .uri(URI.INCIDENT.getLabel())
                )
                .build();
    }
}
