package com.playground.pg.s03.repository;

import com.playground.pg.s03.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductRepo extends ReactiveCrudRepository<Product,Integer> {
    Flux<Product> findByPriceBetween(Integer min,Integer max);
    Flux<Product> findBy(Pageable pageable);
}
