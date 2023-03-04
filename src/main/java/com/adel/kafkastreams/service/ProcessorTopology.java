package com.adel.kafkastreams.service;

import com.adel.kafkastreams.config.KafkaConfig;
import com.adel.kafkastreams.event.EventOne;
import com.adel.kafkastreams.event.EventZero;
import com.adel.kafkastreams.serdes.EventOneSerdes;
import com.adel.kafkastreams.serdes.EventZeroSerdes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProcessorTopology {

    private static final Serde<String> STRING_SERDE = Serdes.String();
    private static final Serde<Long> LONG_SERDE = Serdes.Long();
    private final KafkaConfig kafkaConfig;

    @Autowired
    public void buildPipeline(final StreamsBuilder streamsBuilder) {
        final KStream<String, EventZero> eventZeroKStream = streamsBuilder
                .stream("K_TOPIC_0", Consumed.with(STRING_SERDE, EventZeroSerdes.serdes()))
                .peek((key, data) -> log.info("EventZero received with key=" + key + ", data=" + data));

        // it can then be filter, branch merge etc

        eventZeroKStream
                .map((key, zeroData) -> new KeyValue<>(
                        key,
                        EventOne.builder()
                                .name(zeroData.getName())
                                .build()
                ))
                .to("K_TOPIC_1", Produced.with(STRING_SERDE, EventOneSerdes.serdes()));


    }


}
