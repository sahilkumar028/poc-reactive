package com.playground.pg.test.sec02;

import com.playground.pg.s02.repository.ProductRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import reactor.test.StepVerifier;

public class L02ProductRepository extends AbstractTest  {
    public static final Logger log= LoggerFactory.getLogger(L02ProductRepository.class);
    @Autowired
    public ProductRepo productRepo;
    @Test
    public void findProduct(){
//        System.out.println(productRepo);
        this.productRepo.findAll()
                .doOnNext(p->log.info("{}",p))
                .as(StepVerifier::create)
                .expectNextCount(10)
                .expectComplete()
                .verify();
    }
    @Test
    public void findBetween(){
        this.productRepo.findByPriceBetween(900,3000)
                .doOnNext(p->log.info("{}",p))
                .as(StepVerifier::create)
                .expectNextCount(4)
                .expectComplete()
                .verify();
    }
    @Test
    public void findByPaging(){
        this.productRepo.findBy(PageRequest.of(0,3).withSort(Sort.by("price").ascending()))
                .doOnNext(p->log.info("{}",p))
                .as(StepVerifier::create)
                .assertNext(p-> Assertions.assertEquals(200,p.getPrice()))
                .assertNext(p-> Assertions.assertEquals(250,p.getPrice()))
                .assertNext(p-> Assertions.assertEquals(300,p.getPrice()))
                .expectComplete()
                .verify();
    }
}
