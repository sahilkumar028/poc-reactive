package com.playground.pg.test.sec05;

import com.playground.pg.s05.dto.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient
@SpringBootTest(properties = "sec=s05")
public class CustomerServiceTest {
    @Autowired
    private WebTestClient client;

    @Test
    public void unauthorized(){
        this.client.get()
                .uri("/customer")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.UNAUTHORIZED);
        this.validateGet("secret13",HttpStatus.UNAUTHORIZED);
//        this.validatePost("secret123",HttpStatus.FORBIDDEN);
    }
    @Test
    public void standardCategory(){
        this.validateGet("secret123",HttpStatus.OK);
        this.validatePost("secret123",HttpStatus.FORBIDDEN);
    }
    @Test
    public void primeCategory(){
        this.validateGet("secret456",HttpStatus.OK);
        this.validatePost("secret456",HttpStatus.OK);
    }

    private void validateGet(String token, HttpStatus expectedStatus){
        this.client.get()
                .uri("/customer")
                .header("auth-token",token)
                .exchange()
                .expectStatus().isEqualTo(expectedStatus);
    }
    private void validatePost(String token, HttpStatus expectedStatus){
        var dto=new CustomerDto(null,"sahil","sahil@gmail.com");
        this.client.post()
                .uri("/customer")
                .bodyValue(dto)
                .header("auth-token",token)
                .exchange()
                .expectStatus().isEqualTo(expectedStatus);
    }

}
