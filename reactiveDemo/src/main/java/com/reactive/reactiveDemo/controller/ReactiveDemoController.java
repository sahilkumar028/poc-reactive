package com.reactive.reactiveDemo.controller;


import com.reactive.reactiveDemo.model.Customer;
import com.reactive.reactiveDemo.model.Order;
import com.reactive.reactiveDemo.service.ReactiveDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/testApi")
public class ReactiveDemoController {
    @Autowired
    private ReactiveDemoService reactiveDemoServicee;
//    @GetMapping("/check")
//    public Mono<String> getCheck(){
//        return reactiveDemoServicee.message()
//                .zipWith(reactiveDemoServicee.getNameFromDB())
//                .map(value->{
//                    return value.getT1()+value.getT2();
//                });
//    }

    @PostMapping("/createCustomer")
    public Mono<Customer> createCustomer(@RequestBody Customer customer){
        return reactiveDemoServicee.saveCustomer(customer);
    }
    @PostMapping("/createOrder")
    public Mono<Order> createrOrder(@RequestBody Order order){
        return reactiveDemoServicee.saveOrder(order);
    }
    @GetMapping("/search/{name}")
    public Flux<Customer> findCustomerByName(@PathVariable String name){
        return reactiveDemoServicee.getCustomerByName(name);
    }
    @GetMapping("/customer/find-by-id")
    public Mono<Customer> findCustomerById(@RequestParam("CustomerId") String customerId){
        return reactiveDemoServicee.getCustomerById(customerId);
    }
    @GetMapping("/sales/summary")
    /**
     * Expected Output
     * Sahil: Sum(Order Total)
     * alekh: Sum(Order Total)
     * amit: Sum(Order Total)
     * */
    public Mono<Map<String,Double>> getSummary(){
        return reactiveDemoServicee.getSalesSummary();
    }
}
