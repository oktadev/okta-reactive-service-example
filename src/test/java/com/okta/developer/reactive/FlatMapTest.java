package com.okta.developer.reactive;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class FlatMapTest {

    private static Logger logger = LoggerFactory.getLogger(FlatMapTest.class);

    public static class Word {

        private String word;
        private List<Phonetic> phonetics;
        private List<Meaning> meanings;

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public List<Phonetic> getPhonetics() {
            return phonetics;
        }

        public void setPhonetics(List<Phonetic> phonetics) {
            this.phonetics = phonetics;
        }

        public List<Meaning> getMeanings() {
            return meanings;
        }

        public void setMeanings(List<Meaning> meanings) {
            this.meanings = meanings;
        }
    }

    public static class Meaning {
        private String partOfSpeech;
        private List<Definition> definitions;

        public String getPartOfSpeech() {
            return partOfSpeech;
        }

        public void setPartOfSpeech(String partOfSpeech) {
            this.partOfSpeech = partOfSpeech;
        }

        public List<Definition> getDefinitions() {
            return definitions;
        }

        public void setDefinitions(List<Definition> definitions) {
            this.definitions = definitions;
        }
    }

    public static class Definition {
        private String definition;
        private String example;

        public String getDefinition() {
            return definition;
        }

        public void setDefinition(String definition) {
            this.definition = definition;
        }

        public String getExample() {
            return example;
        }

        public void setExample(String example) {
            this.example = example;
        }
    }

    public static class Phonetic {

        private String text;
        private String audio;


        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getAudio() {
            return audio;
        }

        public void setAudio(String audio) {
            this.audio = audio;
        }
    }

    @Configuration
    public static class FlatMapTestConfig {

        @Bean
        public WebClient webClient(){

            WebClient webClient = WebClient.builder()
                    .baseUrl("https://api.dictionaryapi.dev/api/v2/entries/en_US")
                    .build();

            return webClient;
        }
    }

    @Autowired
    private WebClient webClient;

    @Test
    public void flatMapTest() throws InterruptedException {

        List<String> words = new ArrayList<>();
        words.add("car");
        words.add("key");
        words.add("ballon");
        Flux.fromStream(words.stream())
                .flatMap(w -> getPhonetic(w))
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(y -> logger.info(y));

        Thread.sleep(5000);
    }


    private  Mono<String> getPhonetic(String word){
        Mono<String> response = webClient.get()
                .uri("/" + word).retrieve().bodyToMono(Word[].class)
                .map(r -> r[0].getPhonetics().get(0).getText());

        return response;

    }

}
