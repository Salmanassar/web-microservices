package com.atos.photo.app.apigateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;


@Configuration
public class GlobalFiltersConfiguration {
    private Logger logger = LoggerFactory.getLogger(GlobalFiltersConfiguration.class);

    /**
     * For the @Order with 1 value Pre-Filter will be executed first but
     * for the Post-filter will be executed last because lower value (from 1 to n)  has less priority
     **/

    @Order(1)
    @Bean
    public GlobalFilter firstGlobalPreFilter() {
        return (exchange, chain) -> {
            logger.info("It is First Global Pre-filter is executed ...");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logger.info("It is First Global Post-filter is executed ...but it was a third from them");
            }));
        };
    }

    @Order(2)
    @Bean
    public GlobalFilter secondGlobalPreFilter() {
        return (exchange, chain) -> {
            logger.info("It is Second Global Pre-filter is executed ...");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logger.info("It is Second Global Post-filter is executed ...but it was a second from them");
            }));
        };
    }

    @Order(3)
    @Bean
    public GlobalFilter thirdGlobalPreFilter() {
        return (exchange, chain) -> {
            logger.info("It is Third Global Pre-filter is executed ...");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logger.info("It is Third Global Post-filter is executed ... but it was a first from them");
            }));
        };
    }
}
