package com.shu.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChatDTO {

    private List<Message> messages;
}
