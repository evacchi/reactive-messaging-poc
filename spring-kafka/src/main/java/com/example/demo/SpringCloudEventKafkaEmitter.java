package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.example.demo.common.CloudEventEmitter;
import com.example.demo.common.Event;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.kafka.sender.SenderOptions;

@Component
public class SpringCloudEventKafkaEmitter implements CloudEventEmitter {

    private static final Logger log = LoggerFactory.getLogger(SpringCloudEventKafkaEmitter.class.getName());

    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String TOPIC = "demo-topic";

    private final KafkaProducer<String, String> sender;
    private final SimpleDateFormat dateFormat;
    private final String bootstrapServers;
    private final String topic;

    public SpringCloudEventKafkaEmitter() {
        this.bootstrapServers = "localhost:9092";
        this.topic = "demo-topic";

        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "bootstrapServers");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "sample-producer");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        SenderOptions<String, String> senderOptions = SenderOptions.create(props);

        sender = new KafkaProducer<String, String>(props);
        dateFormat = new SimpleDateFormat("HH:mm:ss:SSS z dd MMM yyyy");
    }

    public CompletionStage<Void> emit(Event event) {
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, "some-key", event.payload());
        Future<RecordMetadata> future = sender.send(record);
        return CompletableFuture.supplyAsync(() -> {
            try {
                // not arguing that this is good design of course
                RecordMetadata recordMetadata = future.get();
                return null;
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void close() {
        sender.close();
    }
}

