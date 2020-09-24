package org.acme;

import java.util.concurrent.CompletionStage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.example.demo.common.CloudEventEmitter;
import com.example.demo.common.Event;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

/**
 * the quarkus implementation just delegates to a real emitter,
 * since smallrye reactive messaging handles different transports
 *
 */
@ApplicationScoped
public class QuarkusKafkaCloudEventEmitter implements CloudEventEmitter {
    @Inject
    @Channel("output_stream")
    Emitter<String> emitter;

    @Override
    public CompletionStage<Void> emit(Event e) {
        return emitter.send(e.payload());
    }
}
