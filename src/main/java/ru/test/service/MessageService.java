package ru.test.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.test.model.Message;
import ru.test.repository.MessageRepository;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public Iterable<Message> saveAll(List<Message> message) {
        try {
            return messageRepository.saveAll(message);
        } catch (Exception e) {
            // TODO(a.fink): 29.01.2020  
        }
        return Collections.emptyList();
    }
}
