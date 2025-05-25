package com.ultra.rmq.service;

import com.ultra.rmq.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketMessageHandler {
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * Send message to a specific user
     * @param userId The recipient's user ID
     * @param message The message to send
     */
    public void sendMessageToUser(Long userId, MessageDto message) {
        messagingTemplate.convertAndSendToUser(
            String.valueOf(userId),
            "/queue/messages",
            message
        );
    }
}
