package org.acme;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.example.demo.common.Event;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Channel;

@ApplicationScoped
public class EventConsumerFactory {

    @Channel("event_stream") Multi<String> events;

    @Produces
    @Named("event_publisher")
    public Multi<Event> makeMulti() {
        return events.map(Event::new);
    }
}