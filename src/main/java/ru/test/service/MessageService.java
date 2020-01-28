package ru.test.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.test.model.Message;
import ru.test.repository.MessageRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public Iterable<Message> saveAll(List<Message> message) {
        return messageRepository.saveAll(message);
    }
}
