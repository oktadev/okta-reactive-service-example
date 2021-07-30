package com.okta.developer.reactive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestUtils {

    public static Logger logger = LoggerFactory.getLogger(TestUtils.class);

    public static <T> T debug(T el, String function) {
        logger.info("element \""+ el + "\" [" + function + "]");
        return el;
    }

    public static <T> T debug1(T el, String function) {
        System.out.println("element \""+ el + "\" [" + function + "] [" + Thread.currentThread().getName() + "]");
        return el;
    }

}
