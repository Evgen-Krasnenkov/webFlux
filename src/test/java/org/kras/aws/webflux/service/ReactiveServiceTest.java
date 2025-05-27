package org.kras.aws.webflux.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ReactiveServiceTest {
    private Consumer<String> consumer = null;

    @BeforeEach
    public void setUp() {
        log.info("Setting up ReactiveServiceTest...");
        consumer = s -> {
            Strings.toRootUpperCase(s);
            log.info("Received: {}", s);
            assertNotNull(s);
        };
    }

    @Test
    public void whenString_thenToUpperCaseAndAssertNotNull() {
        Mono<String> stringMono = Mono.just("Hello, World!")
                .log();

        stringMono.subscribe(consumer, e -> log.error("Error occurred: {}", e.getMessage()));
    }



    @Test
    public void whenFlux_thenToUpperCaseAndAssertNotNull() {
        Flux<String> fluxList = Flux.just("Hello", "World", "New", "Flux")
                .map(String::toUpperCase)
                .log();
        Disposable subscribe = fluxList.subscribe(consumer, e -> log.error("Error occurred: {}", e.getMessage()));
    }
}