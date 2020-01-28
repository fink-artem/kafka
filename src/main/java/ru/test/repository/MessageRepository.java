package ru.test.repository;

import org.springframework.data.repository.CrudRepository;
import ru.test.model.Message;

public interface MessageRepository extends CrudRepository<Message, String> {
}
