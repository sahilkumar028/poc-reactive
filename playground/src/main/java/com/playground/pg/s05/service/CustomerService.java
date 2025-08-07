package com.playground.pg.s05.service;

import com.playground.pg.s05.dto.CustomerDto;
import com.playground.pg.s05.mapper.EntityDtoMapper;
import com.playground.pg.s05.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public Flux<CustomerDto> getAllCustomer(){
        return this.customerRepository.findAll()
                .map(c-> EntityDtoMapper.toDto(c));
    }
    public Flux<CustomerDto> getAllCustomer(Integer page, Integer size){
        return this.customerRepository.findBy(PageRequest.of(page-1,size))
                .map(c-> EntityDtoMapper.toDto(c));
    }
    public Mono<CustomerDto> getCustomerById(Integer id){
        return this.customerRepository.findById(id)
                .map(EntityDtoMapper::toDto);
    }
    public Mono<CustomerDto> saveCustmer(Mono<CustomerDto> customer){
        return customer.map(EntityDtoMapper::toEntity)
                .flatMap(e->this.customerRepository.save(e))
                .map(EntityDtoMapper::toDto);
    }
    public Mono<CustomerDto> updateCustomer(Integer id, Mono<CustomerDto> dto){
        return this.customerRepository.findById(id)
                .flatMap(c->dto)
                .map(EntityDtoMapper::toEntity)
                .doOnNext(c->c.setId(id))
                .flatMap(this.customerRepository::save)
                .map(EntityDtoMapper::toDto);
    }
    public Mono<Boolean> deleteCustomerById(Integer id){
        return this.customerRepository.deleteCustomerById(id);

    }
}
