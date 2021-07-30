package com.okta.developer.reactive.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.security.SecureRandom;

@Service
@Primary
public class SecureRandomReactiveImpl implements SecureRandomService {

    private SecureRandom secureRandom;

    public SecureRandomReactiveImpl() {
        secureRandom = new SecureRandom();
    }

    @Override
    public Mono<Integer> getRandomInt() {
        return Mono.fromCallable(secureRandom::nextInt)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
