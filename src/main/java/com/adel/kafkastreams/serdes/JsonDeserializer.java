package com.adel.kafkastreams.serdes;

import com.adel.kafkastreams.mapper.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class JsonDeserializer<T> implements Deserializer<T> {

    private final Class<T> destinationClass;

    @Override
    public T deserialize(String s, byte[] bytes) {
        return JsonMapper.readFromJson(new String(bytes, StandardCharsets.UTF_8), destinationClass);
    }
}
