package ru.test.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
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
        } catch (DataIntegrityViolationException e) {
            log.error(String.format("Not valid data in request, %s", message), e);
            return Collections.emptyList();
        } catch (Exception e) {
            log.error(String.format("Error during save message, %s", message), e);
            throw e;
        }
    }
}
