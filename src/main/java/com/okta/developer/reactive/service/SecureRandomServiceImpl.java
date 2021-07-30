package com.okta.developer.reactive.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.SecureRandom;

@Service
public class SecureRandomServiceImpl implements SecureRandomService {

    private SecureRandom secureRandom;

    public SecureRandomServiceImpl() {
        secureRandom = new SecureRandom();
    }

    @Override
    public Mono<Integer> getRandomInt() {
        return Mono.just(secureRandom.nextInt());
    }

}
