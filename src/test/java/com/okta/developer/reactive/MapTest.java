package com.okta.developer.reactive;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;


public class MapTest {

    private static Logger logger = LoggerFactory.getLogger(MapTest.class);

    @Test
    public void mapTest() {
        Flux.range(1, 5)
                .map(v -> transform(v))
                .subscribe(y -> logger.info(y));

    }

    private String transform(Integer i){
        return  String.format("%03d", i);
    }
}
