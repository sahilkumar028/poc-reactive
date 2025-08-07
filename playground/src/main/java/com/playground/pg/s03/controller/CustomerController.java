package com.playground.pg.s03.controller;

import com.playground.pg.s03.dto.CustomerDto;
import com.playground.pg.s03.service.CustomerService;
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
    public Mono<ResponseEntity<CustomerDto>> getCustomerById(@PathVariable Integer id){
        return this.customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<CustomerDto>> saveCustomer(@RequestBody Mono<CustomerDto> customerDto){
        return this.customerService.saveCustmer(customerDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.unprocessableEntity().build());
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<CustomerDto>> updateCustomer(@PathVariable Integer id,@RequestBody Mono<CustomerDto> customer){
        return this.customerService.updateCustomer(id,customer)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteCustomer(@PathVariable Integer id){
        return this.customerService.deleteCustomerById(id)
                .filter(b->b)
                .map(b-> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


}
