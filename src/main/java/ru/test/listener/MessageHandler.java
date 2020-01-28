package ru.test.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.test.dto.MessageDto;
import ru.test.mapper.MessageMapper;
import ru.test.model.Message;
import ru.test.service.MessageService;

@Slf4j
@Component
@AllArgsConstructor
public class MessageHandler {

    private final MessageService messageService;
    private final MessageMapper messageMapper;

    public void handle(MessageDto messageDto) {
        Message message = messageMapper.messageDtoToMessage(messageDto);
        messageService.save(message);
    }

}
