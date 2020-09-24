package com.example.demo.common.data;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.example.demo.common.CloudEventEmitter;
import com.example.demo.common.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("event_emitter")
@Named("event_emitter")
@ApplicationScoped
@Component
public class LoggingEmitter implements CloudEventEmitter {

    Logger logger = LoggerFactory.getLogger(LoggingEmitter.class);

    public CompletionStage<Void> emit(Event event) {
        return CompletableFuture.supplyAsync(() -> {logger.info("Emitted: {}", event) ; return null;});
    }
}
