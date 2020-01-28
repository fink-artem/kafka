package ru.test.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.test.dto.MessageDto;

@Slf4j
@Component
@AllArgsConstructor
public class MessageListener {

    private final ObjectMapper objectMapper;
    private final MessageHandler messageHandler;

    @KafkaListener(topics = "message.topic")
    public void consume(MessageDto dto) throws JsonProcessingException {
        log.info("=> consumed {}", writeValueAsString(dto));
        messageHandler.handle(dto);
    }

    private String writeValueAsString(MessageDto dto) throws JsonProcessingException {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            log.error("Writing value to JSON failed: " + dto.toString());
            throw e;
        }
    }
}
