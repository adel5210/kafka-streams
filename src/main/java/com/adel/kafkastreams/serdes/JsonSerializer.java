package com.adel.kafkastreams.serdes;

import com.adel.kafkastreams.mapper.JsonMapper;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;

@NoArgsConstructor
public class JsonSerializer<T> implements Serializer<T> {
    @Override
    public byte[] serialize(String s, T t) {
        return JsonMapper.writeToJson(t).getBytes(StandardCharsets.UTF_8);
    }
}
