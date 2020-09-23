package com.example.demo;

import java.util.concurrent.CountDownLatch;

import com.example.demo.common.CloudEventEmitter;
import com.example.demo.common.Event;
import com.example.demo.common.EventSubscriber;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication(
		scanBasePackages = {
				"com.example.demo.**",
				"com.example.demo.common.**",
				"com.example.demo.common.data.**"})
public class DemoApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(CommandLineRunner.class.getName());

	@Autowired
	@Qualifier("event_publisher")
	Publisher<Event> publisher;

	@Autowired
	CloudEventEmitter emitter;

	@Override
	public void run(String... args) throws Exception {
		log.info("startup");
		Flux.from(publisher)
				.map(e -> { System.out.println(e); return e;})
				.subscribe(emitter::emit);
	}

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(DemoApplication.class, args);
		// hang so that it won't quit immediately
		new CountDownLatch(1).await();
	}
}
