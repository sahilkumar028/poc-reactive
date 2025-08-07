package com.playground.pg.s04.controller;

import com.playground.pg.s04.dto.CustomerDto;
import com.playground.pg.s04.exceptions.ApplicationException;
import com.playground.pg.s04.service.CustomerService;
import com.playground.pg.s04.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Flux<CustomerDto> allCustomer(){
        return this.customerService.getAllCustomer();
    }

    @GetMapping("paginated")
    public Mono<List<CustomerDto>> allCustomer(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "3") Integer size){
        return this.customerService.getAllCustomer(page, size)
                .collectList();
    }

    @GetMapping("{id}")
    public Mono<CustomerDto> getCustomerById(@PathVariable Integer id){
        return this.customerService.getCustomerById(id)
                .switchIfEmpty(ApplicationException.customerNotFound(id));
    }

    @PostMapping
    public Mono<CustomerDto> saveCustomer(@RequestBody Mono<CustomerDto> customerDto){
        return customerDto.transform(RequestValidator.validate())
                .as(m -> this.customerService.saveCustmer(m));
    }

    @PutMapping("{id}")
    public Mono<CustomerDto> updateCustomer(@PathVariable Integer id, @RequestBody Mono<CustomerDto> customer){
        return customer.transform(RequestValidator.validate())
                .as(m-> this.customerService.updateCustomer(id,m))
                .switchIfEmpty(ApplicationException.customerNotFound(id));

    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteCustomer(@PathVariable Integer id){
        return this.customerService.deleteCustomerById(id)
                .filter(b->b)
                .switchIfEmpty (ApplicationException.customerNotFound(id))
                .then();
    }


}
