package com.playground.pg.test.sec02;

import com.playground.pg.s02.entity.Customer;
import com.playground.pg.s02.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

public class L01CustomerRepositoryTest extends AbstractTest{
    public static final Logger log= LoggerFactory.getLogger(L01CustomerRepositoryTest.class);
    @Autowired
    private CustomerRepository repository;

    @Test
    public void findAll(){
        this.repository.findAll()
            .doOnNext(c->log.info("{}",c))
            .as(StepVerifier::create)
            .expectNextCount(10)
            .expectComplete()
            .verify();
    }
    @Test
    public void findById(){
        this.repository.findById(2)
            .doOnNext(c->log.info("{}",c))
            .as(StepVerifier::create)
            .assertNext(c-> Assertions.assertEquals("mike",c.getName()))
            .expectComplete()
            .verify();
    }
    @Test
    public void findByName(){
        this.repository.findByName("jake")
            .doOnNext(c->log.info("{}",c))
            .as(StepVerifier::create)
            .assertNext(c-> Assertions.assertEquals("jake@gmail.com",c.getEmail()))
            .expectComplete()
            .verify();
    }
    @Test
    public void findByEmailEndingWith(){
        this.repository.findByEmailEndingWith("ke@gmail.com")
                .doOnNext(c->log.info("{}",c))
                .as(StepVerifier::create)
                .assertNext(c->Assertions.assertEquals("mike@gmail.com",c.getEmail()))
                .assertNext(c-> Assertions.assertEquals("jake@gmail.com",c.getEmail()))
                .expectComplete()
                .verify();
    }
    @Test
    public void insertAndDeleteCustomer(){
        this.repository.save(
                new Customer(null,"sahil","sahil@gmail.com")
        ).doOnNext(c->log.info("{}",c))
                .as(StepVerifier::create)
                .assertNext(c->Assertions.assertNotNull(c.getId()))
                .expectComplete()
                .verify();

        this.repository.count()
                .as(c->StepVerifier.create(c))
                .expectNext(15l)
                .expectComplete()
                .verify();
        this.repository.deleteById(11)
                .then(this.repository.count())
                .as(StepVerifier::create)
                .expectNext(14l)
                .expectComplete()
                .verify();
    }
    @Test
    public void updateCustomer(){
        this.repository.findByName("sahil")
                .doOnNext(c->c.setName("sahil kumar"))
                .flatMap(c->this.repository.save(c))
                .doOnNext(c->log.info("{}",c))
                .as(StepVerifier::create)
                .assertNext(c->Assertions.assertEquals("sahil kumar",c.getName()))
                .expectComplete()
                .verify();
    }

}

