package com.example.demo.common;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventSubscriber implements Subscriber<Event> {
    private static final Logger log = LoggerFactory.getLogger(EventSubscriber.class.getName());
    private Subscription s;

    @Override
    public void onSubscribe(Subscription s) {
        this.s = s;
        s.request(1);
    }

    @Override
    public void onNext(Event event) {
        s.request(1);
        log.info("Received {}", event.toString());

    }

    @Override
    public void onError(Throwable t) {
        log.error("Caught error", t);
    }

    @Override
    public void onComplete() {
        log.debug("Stream completed.");
    }
}
