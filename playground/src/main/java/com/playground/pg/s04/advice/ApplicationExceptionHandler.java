package com.playground.pg.s04.advice;

import com.playground.pg.s04.exceptions.CustomerNotFoundException;
import com.playground.pg.s04.exceptions.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ProblemDetail handleException(CustomerNotFoundException ex){
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,ex.getMessage());
        problem.setType(URI.create("http://example.com/problem/customer-not-found"));
        problem.setTitle("Customer not Found");
        return problem;
    }
    @ExceptionHandler(InvalidInputException.class)
    public ProblemDetail handleException(InvalidInputException ex){
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,ex.getMessage());
        problem.setType(URI.create("http://example.com/problem/invalid-request"));
        problem.setTitle("invalid request");
        return problem;
    }
}
