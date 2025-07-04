package com.example.demo.s02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class LazyStream01 {
    private static final Logger log = LoggerFactory.getLogger(LazyStream01.class);
    public static void main(String[] args){
        Stream.of(1)
                .peek(i->log.info("received:{}",i))
                .toList();
    }
}
