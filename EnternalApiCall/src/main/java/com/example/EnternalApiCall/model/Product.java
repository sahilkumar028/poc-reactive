package com.example.EnternalApiCall.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String productId;
    private Long id;
    private String title;
    private String description;
    private String category;
    private double price;
    private double discountPercentage;
}
