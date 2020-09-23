package com.example.demo.common;

import java.util.concurrent.CompletionStage;

public interface CloudEventEmitter {
    public CompletionStage<Void> emit(Event event);
}
