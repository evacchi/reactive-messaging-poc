package org.acme;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.demo.common.Event;
import com.example.demo.common.EventSubscriber;
import io.quarkus.runtime.Startup;
import org.reactivestreams.Publisher;

@Startup
@ApplicationScoped
public class DemoConsumer {

    @Named("event_publisher")
    Publisher<Event> publisher;

    @PostConstruct
    void observer() {
        publisher.subscribe(new EventSubscriber());
    }
}
