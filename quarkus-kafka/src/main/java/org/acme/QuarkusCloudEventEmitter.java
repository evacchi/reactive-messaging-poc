package org.acme;

import java.util.concurrent.CompletionStage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.example.demo.common.CloudEventEmitter;
import com.example.demo.common.Event;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class QuarkusCloudEventEmitter implements CloudEventEmitter {
    @Inject
    Emitter<Event> emitter;

    @Override
    public CompletionStage<Void> emit(Event e) {
        return emitter.send(e);
    }
}
