package org.acme;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.demo.common.CloudEventEmitter;
import com.example.demo.common.Event;
import com.example.demo.common.EventSubscriber;
import io.quarkus.runtime.Startup;
import io.smallrye.mutiny.Multi;
import org.reactivestreams.Publisher;

@Startup
@ApplicationScoped
public class DemoConsumer {

    @Named("event_publisher")
    Publisher<Event> publisher;

    @Inject
    QuarkusCloudEventEmitter emitter;

    @PostConstruct
    void observer() {
        Multi.createFrom().publisher(publisher)
                .map(x -> { System.out.println(x); return x; })
                .subscribe()
                .with(emitter::emit);
    }
}
