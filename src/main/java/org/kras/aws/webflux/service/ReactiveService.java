package org.kras.aws.webflux.service;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReactiveService {

    private final Environment environment;

    public ReactiveService(Environment environment) {
        this.environment = environment;
    }

    public Mono<LocalDateTime> getCurrentTime() {
        return Mono.just(LocalDateTime.now());
    }

    public Mono<Map<String, String>> getEnvironmentVariables() {
        Map<String, String> envVariables = new HashMap<>();
        for (String propertyName : environment.getActiveProfiles()) {
            String propertyValue = environment.getProperty(propertyName);
            if (propertyValue != null) {
                envVariables.put(propertyName, propertyValue);
            }
        }
        return Mono.just(envVariables);
    }
}
