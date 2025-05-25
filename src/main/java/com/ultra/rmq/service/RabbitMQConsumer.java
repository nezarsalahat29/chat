package com.ultra.rmq.service;

import com.ultra.rmq.dto.MessageDto;
import com.ultra.rmq.entity.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {
    
    @Autowired
    private WebSocketMessageHandler webSocketMessageHandler;

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveMessage(Message message) {
        // Convert message to DTO
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setSenderId(message.getSenderId());
        messageDto.setReceiverId(message.getReceiverId());
        messageDto.setContent(message.getContent());
        messageDto.setTimestamp(message.getTimestamp());
        messageDto.setStatus(message.getStatus());

        // Send message to the receiver's WebSocket connection
        webSocketMessageHandler.sendMessageToUser(message.getReceiverId(), messageDto);
    }
}
