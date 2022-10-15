package com.yicj.hello.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class HelloMDC {

    public static void main(String[] args) {
        MDC.put("first", "Dorothy");
        Logger logger = LoggerFactory.getLogger(HelloMDC.class) ;
        MDC.put("last", "Parker");
        logger.info("Check enclosed .");
        logger.debug("The most beautiful two words in English .");

        MDC.put("first", "Richard");
        MDC.put("last", "Nixon");
        logger.info("I am not a crook .");
        logger.info("Attributed to the former US president. 17 Nov 1973.");
    }
}
