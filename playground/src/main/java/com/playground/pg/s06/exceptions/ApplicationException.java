package com.playground.pg.s06.exceptions;

import reactor.core.publisher.Mono;

public class ApplicationException{
    public static <T> Mono<T> customerNotFound(Integer id){
        return Mono.error(new CustomerNotFoundException(id));
    }
    public static <T> Mono<T> missingName(){
        return Mono.error(new InvalidInputException("Name is required"));
    }
    public static <T> Mono<T> missingValidEmail(){
        return Mono.error(new InvalidInputException("valid Email required"));
    }

}
