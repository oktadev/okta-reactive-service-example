package com.okta.developer.reactive.service;

import reactor.core.publisher.Mono;

public interface SecureRandomService {

    Mono<Integer> getRandomInt();
}
