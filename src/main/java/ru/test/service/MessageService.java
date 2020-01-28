package ru.test.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.test.model.Message;
import ru.test.repository.MessageRepository;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public Message save(Message message) {
        return messageRepository.save(message);
    }
}
