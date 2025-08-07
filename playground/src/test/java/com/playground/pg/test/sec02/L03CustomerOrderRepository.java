package com.playground.pg.test.sec02;

import com.playground.pg.s02.repository.CustomerOrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

public class L03CustomerOrderRepository extends AbstractTest {
    public static final Logger log= LoggerFactory.getLogger(L03CustomerOrderRepository.class);
    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Test
    public void productOrderedByCustomer(){
        this.customerOrderRepository.getProductsOrderedByCustomer("mike")
                .doOnNext(p->log.info("{}",p))
                .as(StepVerifier::create)
                .expectNextCount(2)
                .expectComplete()
                .verify();
    }
    @Test
    public void orderDetailsByProduct(){
        this.customerOrderRepository.getOrderDetailsByProduct("iphone 20")
                .doOnNext(dto ->log.info("{}", dto))
                .as(StepVerifier::create)
                .assertNext(dto-> Assertions.assertEquals(975,dto.amount()))
                .assertNext(dto-> Assertions.assertEquals(950,dto.amount()))
                .expectComplete()
                .verify();
    }
}
