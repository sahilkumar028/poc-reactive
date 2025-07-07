package com.example.EnternalApiCall.Scheduler;

import com.example.EnternalApiCall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductScheduler {
    private final ProductService productService;
    @Scheduled(fixedRate = 120000)
    public void fetchDataEveryTwoMinutes(){
        productService.fetchProduct()
                .subscribe(
                        product -> {
                            productService.saveProduct(product)
                                    .subscribe(i->System.out.println("product fetch "+product));
                        },
                        error->System.err.println("faild to fetch product"+error)
                );
    }
}
