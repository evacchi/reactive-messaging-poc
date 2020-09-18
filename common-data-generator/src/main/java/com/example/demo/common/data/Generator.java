package com.example.demo.common.data;

import java.time.Duration;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.example.demo.common.Event;
import io.smallrye.mutiny.Multi;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@ApplicationScoped
@Component
public class Generator {
    @Bean
    @Qualifier("event_publisher")
    @Named("event_publisher")
    @Produces
    Publisher<Event> test(){
        return Multi.createFrom().ticks().every(Duration.ofSeconds(1))
                .map(t -> new Event("fake"+t));
    }
}
