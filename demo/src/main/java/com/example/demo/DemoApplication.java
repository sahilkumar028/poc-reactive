package com.example.demo;

import com.example.demo.s01.publisher.PublisherImpl;
import com.example.demo.s01.subscriber.SubscriberImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
//	}
	public static void main(String[] args) throws InterruptedException {
		demo3();
	}

	private static void demo1(){
		var publisher =new PublisherImpl();
		var subscriber =new SubscriberImpl();
		publisher.subscribe(subscriber);
	}

	private static void demo2() throws InterruptedException {
		var publisher =new PublisherImpl();
		var subscriber =new SubscriberImpl();
		publisher.subscribe(subscriber);
		subscriber.getSubscription().request(3);
		Thread.sleep(2000);
		subscriber.getSubscription().request(3);
		Thread.sleep(2000);
		subscriber.getSubscription().request(3);
		Thread.sleep(2000);
		subscriber.getSubscription().request(3);

	}

	private static void demo3() throws InterruptedException {
		var publisher =new PublisherImpl();
		var subscriber =new SubscriberImpl();
		publisher.subscribe(subscriber);
		subscriber.getSubscription().request(3);
		Thread.sleep(2000);
		subscriber.getSubscription().request(11);
		Thread.sleep(2000);
		subscriber.getSubscription().request(3);
		Thread.sleep(2000);
		subscriber.getSubscription().request(3);

	}

}
