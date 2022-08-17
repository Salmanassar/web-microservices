package com.atos.photo.app.apigateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.function.Consumer;
@Component
public class PreFilter implements GlobalFilter, Ordered {
    final Logger logger = LoggerFactory.getLogger(PreFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("PreFilter is executed");
        String getRequestPath = exchange.getRequest().getPath().toString();
        logger.info("The request path is " + getRequestPath);
       HttpHeaders headers  = exchange.getRequest().getHeaders();
       Set<String> headersName = headers.keySet();
       headersName.forEach(headerName->{
           String headerValue = headers.getFirst(headerName);
           logger.info("Header name " + headerValue);
       });
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
