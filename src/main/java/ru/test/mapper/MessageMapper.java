package ru.test.mapper;

import org.springframework.stereotype.Component;
import ru.test.dto.MessageDto;
import ru.test.model.Message;

@Component
public class MessageMapper {

    public Message messageDtoToMessage(MessageDto messageDto) {
        return Message.builder()
                .messageId(messageDto.getMessageId())
                .message(messageDto.getPayload())
                .build();
    }
}
