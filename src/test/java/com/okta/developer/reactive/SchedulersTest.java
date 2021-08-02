package com.okta.developer.reactive;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.okta.developer.reactive.TestUtils.debug;


@SpringBootTest
@ActiveProfiles("test")
public class SchedulersTest {

    private static Logger logger = LoggerFactory.getLogger(SchedulersTest.class);


    @Test
    public void immediateTest() {

        logger.info("Schedulers.immediate()");

        Flux.range(1, 5)
                .map(v -> debug(v, "map"))
                .subscribeOn(Schedulers.immediate())
                .subscribe(w -> debug(w,"subscribe"));
    }

    @Test
    public void singleTest() throws InterruptedException {

        logger.info("Schedulers.single()");


        Flux.range(1, 5)
                .map(v -> debug(v, "map"))
                .publishOn(Schedulers.single())
                .subscribe(w -> debug(w,"subscribe"));


        Thread.sleep(5000);
    }


    @Test
    public void boundedElasticTest() throws InterruptedException {

        logger.info("Schedulers.boundedElastic()");


        List<String> words = new ArrayList<>();
        words.add("a.txt");
        words.add("b.txt");
        words.add("c.txt");
        Flux flux = Flux.fromArray(words.toArray(new String[0]))
                .publishOn(Schedulers.boundedElastic())
                .map(w -> scanFile(debug(w, "map")));

        flux.subscribe(y -> debug(y, "subscribe1"));
        flux.subscribe(y -> debug(y, "subscribe2"));

        Thread.sleep(5000);
    }


    public String scanFile(String filename){

        InputStream stream = getClass().getClassLoader().getResourceAsStream(filename);
        Scanner scanner = new Scanner(stream);
        String line = scanner.nextLine();
        scanner.close();

        return line;
    }

    @Test
    public void parallelTest() throws InterruptedException {

        logger.info("Schedulers.parallel()");


        Flux flux = Flux.range(1, 5)
                .publishOn(Schedulers.parallel())
                .map(v -> debug(v, "map"));

        flux.subscribe(w -> debug(w,"subscribe1"));
        flux.subscribe(w -> debug(w,"subscribe2"));
        flux.subscribe(w -> debug(w,"subscribe3"));
        flux.subscribe(w -> debug(w,"subscribe4"));
        flux.subscribe(w -> debug(w,"subscribe5"));


        Thread.sleep(5000);
    }


    @Test
    public void subscribeOnWithPublishOnTest() throws InterruptedException {

        logger.info("subscribeOn() & publishOn()");

        Flux.range(1, 3)
                .map(v -> debug(v, "map1"))
                .publishOn(Schedulers.parallel())
                .map(v -> debug(v * 100, "map2"))
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(w -> debug(w,"subscribe"));

        Thread.sleep(5000);
    }

}
