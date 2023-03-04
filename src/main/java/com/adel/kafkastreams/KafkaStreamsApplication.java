package com.adel.kafkastreams;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;

@SpringBootApplication
@EnableKafkaStreams
@RequiredArgsConstructor
@Slf4j
public class KafkaStreamsApplication implements CommandLineRunner {

    private final StreamsBuilderFactoryBean streamsBuilderFactoryBean;

    public static void main(String[] args) {
        SpringApplication.run(KafkaStreamsApplication.class, args);
    }

    //https://medium.com/lydtech-consulting/kafka-streams-spring-boot-demo-ff0e74e08c9c
    @Override
    public void run(String... args) throws Exception {
        log.info("Initialize ...");
        log.info("Topology: " + streamsBuilderFactoryBean.getTopology().describe().toString());

        final KafkaStreams kafkaStreams = streamsBuilderFactoryBean.getKafkaStreams();
        ReadOnlyKeyValueStore<String, Long> data = kafkaStreams
                .store(StoreQueryParameters.fromNameAndType("data", QueryableStoreTypes.keyValueStore()));

        data.get("Some DATA");
    }
}
