package com.adel.kafkastreams.serdes;

import com.adel.kafkastreams.event.EventOne;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class EventOneSerdes extends Serdes.WrapperSerde<EventOne> {
    public EventOneSerdes() {
        super(new JsonSerializer<>(), new JsonDeserializer<>(EventOne.class));
    }

    public static Serde<EventOne> serdes() {
        return Serdes.serdeFrom(
                new JsonSerializer<>(),
                new JsonDeserializer<>(EventOne.class)
        );
    }
}
