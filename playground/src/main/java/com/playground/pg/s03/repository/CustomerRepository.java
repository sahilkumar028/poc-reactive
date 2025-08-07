package com.playground.pg.s03.repository;

import com.playground.pg.s03.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer,Integer> {
    @Modifying
    @Query("delete from customer where id=:id")
    public Mono<Boolean> deleteCustomerById(Integer id);

    Flux<Customer> findBy(Pageable pageable);
}
