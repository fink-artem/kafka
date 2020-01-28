package ru.test.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MessageDto {

    private int messageId;
    private String payload;

}
