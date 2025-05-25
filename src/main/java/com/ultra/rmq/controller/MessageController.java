package com.ultra.rmq.controller;

import com.ultra.rmq.dto.CreateMessageRequest;
import com.ultra.rmq.dto.MessageDto;
import com.ultra.rmq.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    
    @Autowired
    private MessageService messageService;

    @PostMapping
    public MessageDto sendMessage(@RequestHeader("X-User-Id") Long userId,
                                @RequestBody CreateMessageRequest request) {
        return messageService.sendMessage(userId, request);
    }

    @GetMapping
    public List<MessageDto> getMessageHistory(@RequestHeader("X-User-Id") Long userId) {
        return messageService.getMessageHistory(userId);
    }
}
