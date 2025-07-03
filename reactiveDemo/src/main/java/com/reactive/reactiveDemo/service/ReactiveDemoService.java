package com.reactive.reactiveDemo.service;


import com.reactive.reactiveDemo.model.Customer;
import com.reactive.reactiveDemo.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class ReactiveDemoService {

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;
//    public Mono<String>  message(){
//        return Mono.just("hello");
//    }
//
//
//    public Mono<String> getNameFromDB(){
//        return Mono.just("sahil")
//                .delayElement(Duration.ofSeconds(3));
//    }
    public Mono<Customer> saveCustomer(Customer customer){
        return reactiveMongoTemplate.save(customer);
    }

    public Mono<Order> saveOrder(Order order){
        return reactiveMongoTemplate.save(order);
    }

    public Flux<Customer> getCustomerByName(String name){
        Criteria criteria=Criteria.where("name").is(name);
        Query query=Query.query(criteria);
        return reactiveMongoTemplate.find(query,Customer.class);
    }
    public Mono<Customer> getCustomerById(String id){
        Criteria criteria=Criteria.where("id").is(id);
        Query query=Query.query(criteria);
        return reactiveMongoTemplate.findOne(query, Customer.class);
    }
    public Mono<Map<String, Double>> getSalesSummary(){
        return reactiveMongoTemplate.findAll(Customer.class)
                .flatMap(e->Mono.zip(Mono.just(e),getTotalFromSales(e.getId())))
                .collectMap(t->t.getT1().getName(),t-> t.getT2());
    }

    private Mono<Double> getTotalFromSales(String customerId){
        Criteria criteria=Criteria.where("customerId").is(customerId);
        Query query=Query.query(criteria);
        return reactiveMongoTemplate.find(query,Order.class)
//                .log()
                .map(Order::getTotal)
                .reduce(0d,Double::sum);

    }
}
