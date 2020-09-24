package org.acme;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.demo.common.CloudEventEmitter;
import com.example.demo.common.Event;
import io.quarkus.runtime.Startup;
import io.smallrye.mutiny.Multi;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Startup
@ApplicationScoped
public class DemoConsumer {

    private static final Logger logger = LoggerFactory.getLogger(DemoConsumer.class);

    @Named("event_publisher")
    Publisher<Event> publisher;

    @Inject
    CloudEventEmitter emitter;

    @PostConstruct
    void observer() {
        Multi.createFrom().publisher(publisher)
                .invoke(x -> logger.info("Received: {}", x))
                .subscribe()
                .with(emitter::emit);
    }
}
