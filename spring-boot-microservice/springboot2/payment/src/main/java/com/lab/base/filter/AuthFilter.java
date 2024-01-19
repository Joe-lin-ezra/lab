package com.lab.base.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Set;

@Component
@Order( Ordered.HIGHEST_PRECEDENCE )
public class AuthFilter implements WebFilter {

	private static final Set<String> permittedPath = Set.of( "/auth");

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

		if ( !permittedPath.contains( exchange.getRequest().getPath().value()) ) {
			exchange.getResponse().setStatusCode( HttpStatus.BAD_REQUEST );
			return exchange.getResponse().setComplete();
		}

		return chain.filter(exchange);
	}
}
