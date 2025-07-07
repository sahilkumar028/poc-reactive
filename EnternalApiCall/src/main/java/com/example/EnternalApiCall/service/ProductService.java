package com.example.EnternalApiCall.service;

import com.example.EnternalApiCall.model.Product;
import com.example.EnternalApiCall.model.ProductList;
import com.example.EnternalApiCall.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final WebClient webClient;
    private final ProductRepo productRepo;

    public Flux<Product> fetchProduct(){
        return webClient.get()
                .uri("/products")
                .retrieve()
                .bodyToMono(ProductList.class)
                .flatMapMany(i-> Flux.fromIterable(i.getProducts()));
    }

    public Mono<Product> saveProduct(Product product){
        return productRepo.save(product);
    }
}
