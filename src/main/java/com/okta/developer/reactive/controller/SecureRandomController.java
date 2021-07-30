package com.okta.developer.reactive.controller;


import com.okta.developer.reactive.service.SecureRandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class SecureRandomController {

    @Autowired
    private SecureRandomService secureRandomService;


    @GetMapping("/random")
    public Mono<SecureRandom> getSecureRandom(){
        return secureRandomService.getRandomInt()
                .map(i -> new SecureRandom(i));
    }
}
