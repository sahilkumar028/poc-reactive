package com.example.EnternalApiCall.repo;

import com.example.EnternalApiCall.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepo extends ReactiveMongoRepository<Product,String> {

}
