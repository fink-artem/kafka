package ru.test.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.test.dto.MessagesDto;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value("${kafka.consumer.group-id}")
    private String groupId;

    @Value("${kafka.consumer.fetch-max-wait}")
    private String fetchMaxWait;

    @Value("${kafka.consumer.fetch-min-size}")
    private String fetchMinSize;

    @Value("${kafka.listener.concurrency}")
    private Integer concurrency;

    @Bean
    public ConsumerFactory<String, MessagesDto> consumerFactory(){
        JsonDeserializer<MessagesDto> deserializer = new JsonDeserializer<>(MessagesDto.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages(MessagesDto.class.getPackage().getName());
        deserializer.setUseTypeMapperForKey(true);

        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        config.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, fetchMinSize);
        config.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, fetchMaxWait);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MessagesDto> kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, MessagesDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        factory.setConcurrency(concurrency);

        return factory;
    }
}
