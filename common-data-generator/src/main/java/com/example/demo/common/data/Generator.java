package com.example.demo.common.data;

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
        return Multi.createFrom().items(
                new Event("fake1"),
                new Event("fake2"),
                new Event("fake3"),
                new Event("fake4"));
    }
}
