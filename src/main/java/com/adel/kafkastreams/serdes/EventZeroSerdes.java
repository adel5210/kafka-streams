package com.adel.kafkastreams.serdes;

import com.adel.kafkastreams.event.EventZero;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class EventZeroSerdes extends Serdes.WrapperSerde<EventZero> {
    public EventZeroSerdes() {
        super(new JsonSerializer<>(),
                new JsonDeserializer<>(EventZero.class));
    }

    public static Serde<EventZero> serdes() {
        return Serdes.serdeFrom(
                new JsonSerializer<>(),
                new JsonDeserializer<>(EventZero.class)
        );
    }
}
