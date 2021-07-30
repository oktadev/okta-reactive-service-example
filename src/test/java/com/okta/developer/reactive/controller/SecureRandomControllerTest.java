package com.okta.developer.reactive.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockOidcLogin;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class SecureRandomControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testGetSecureRandom() {


        webTestClient.mutateWith(mockOidcLogin())
                .get()
                .uri("/random")
                .exchange()
                .expectStatus().isOk();
    }

}
