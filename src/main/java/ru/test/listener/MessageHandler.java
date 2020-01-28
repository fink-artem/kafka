package ru.test.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.test.dto.MessagesDto;
import ru.test.mapper.MessageMapper;
import ru.test.model.Message;
import ru.test.service.MessageService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class MessageHandler {

    private final MessageService messageService;
    private final MessageMapper messageMapper;

    public void handle(MessagesDto messageDto) {
        List<Message> messages = messageDto.getMessages().stream()
                .map(messageMapper::messageDtoToMessage)
                .collect(Collectors.toList());

        messageService.saveAll(messages);
    }

}
