package com.playground.pg.test.sec03;

import com.playground.pg.s03.dto.CustomerDto;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

@AutoConfigureWebTestClient
@SpringBootTest(properties = "sec=s03")
public class CustomerServiceTest {
    public static final Logger log= LoggerFactory.getLogger(CustomerServiceTest.class);

    @Autowired
    private WebTestClient client;
    @Test
    public void allCustomer(){
        this.client.get()
                .uri("/customer")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(CustomerDto.class)
                .value(list->log.info("{}",list))
                .hasSize(10);
    }
    @Test
    public void paginatedCustomer(){
        this.client.get()
                .uri("/customer/paginated?page=3&size=2")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .consumeWith(r->log.info("{}",new String(r.getResponseBody())))
                .jsonPath("$.length()").isEqualTo(2)
                .jsonPath("$[0].id").isEqualTo(5)
                .jsonPath("$[1].id").isEqualTo(6);
    }
    @Test
    public void customerById(){
        this.client.get()
            .uri("/customer/1")
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody()
            .consumeWith(r->log.info("{}",new String(r.getResponseBody())))
            .jsonPath("$.id").isEqualTo(1)
            .jsonPath("$.name").isEqualTo("sam")
            .jsonPath("$.email").isEqualTo("sam@gmail.com");
    }

    @Test
    public void createAndDeleteCustomer(){
        CustomerDto n=new CustomerDto(null,"abhishek","abhishek@gmail.com");
        this.client.post()
                .uri("/customer")
                .bodyValue(n)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .consumeWith(r->log.info("{}",new String(r.getResponseBody())))
                .jsonPath("$.id").isNumber()
                .jsonPath("$.name").isEqualTo("abhishek")
                .jsonPath("$.email").isEqualTo("abhishek@gmail.com");

        this.client.delete()
                .uri("/customer/29")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody().isEmpty();
    }
    @Test
    public void updateCustomer(){
        CustomerDto n=new CustomerDto(null,"rakesh","rakesh@gmail.com");
        this.client.put()
                .uri("/customer/5")
                .bodyValue(n)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .consumeWith(r->log.info("{}",new String(r.getResponseBody())))
                .jsonPath("$.id").isNumber()
                .jsonPath("$.name").isEqualTo("rakesh")
                .jsonPath("$.email").isEqualTo("rakesh@gmail.com");
    }
    @Test
    public void customerNotFound(){
            this.client.get()
                    .uri("/customer/343")
                    .exchange()
                    .expectStatus().is4xxClientError()
                    .expectBody().isEmpty();
        this.client.delete()
                .uri("/customer/343")
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody().isEmpty();
        this.client.put()
                .uri("/customer/343")
                .bodyValue(new CustomerDto(null,null,null))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody().isEmpty();

    }
}
