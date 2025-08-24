package com.ms_spring_brgy.gateway.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.web.server.authentication.ServerBearerTokenAuthenticationConverter;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class CookieConverter implements ServerAuthenticationConverter {

    private final ServerBearerTokenAuthenticationConverter delegate = new ServerBearerTokenAuthenticationConverter();

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return delegate.convert(exchange)
                .switchIfEmpty(Mono.defer(() -> Mono
                        .justOrEmpty(exchange
                                .getRequest()
                                .getCookies()
                                .getFirst("access_token"))
                        .map(cookie -> new BearerTokenAuthenticationToken(cookie.getValue()))
                ));
    }
}