package com.example.demo.common;

public class Event {

    private final String payload;

    public Event(String payload) {
        this.payload = payload;
    }

    public String payload() {
        return payload;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append("payload='").append(payload).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
