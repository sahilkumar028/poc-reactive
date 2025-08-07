package com.playground.pg.s02.repository;

import com.playground.pg.s02.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer,Integer> {
    Flux<Customer> findByName(String name);
    Flux<Customer> findByEmailEndingWith(String email);
}
