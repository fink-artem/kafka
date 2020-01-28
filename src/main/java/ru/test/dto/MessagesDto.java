package ru.test.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class MessagesDto {

    private List<MessageDto> messages;

}
