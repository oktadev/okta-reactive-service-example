package com.okta.developer.reactive.controller;

public class SecureRandom {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public SecureRandom(Integer value){
        this.value = value.toString();
    }
}
