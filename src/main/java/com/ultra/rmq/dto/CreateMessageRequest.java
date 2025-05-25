package com.ultra.rmq.dto;

import lombok.Data;

@Data
public class CreateMessageRequest {
    private Long receiverId;
    private String content;
}
