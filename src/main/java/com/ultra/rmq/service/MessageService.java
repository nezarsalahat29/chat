package com.ultra.rmq.service;

import com.ultra.rmq.dto.CreateMessageRequest;
import com.ultra.rmq.dto.MessageDto;
import com.ultra.rmq.entity.Message;
import com.ultra.rmq.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    
    @Autowired
    private MessageRepository messageRepository;
    
    @Autowired
    private MessageProducer messageProducer;

    public MessageDto sendMessage(Long senderId, CreateMessageRequest request) {
        Message message = new Message();
        message.setSenderId(senderId);
        message.setReceiverId(request.getReceiverId());
        message.setContent(request.getContent());
        message.setTimestamp(LocalDateTime.now());
        message.setStatus(Message.MessageStatus.SENT);

        Message savedMessage = messageRepository.save(message);
        
        // Send message to RabbitMQ using producer
        messageProducer.sendMessage(savedMessage);
        
        return convertToDto(savedMessage);
    }

    public List<MessageDto> getMessageHistory(Long userId) {
        List<Message> messages = messageRepository.findBySenderIdOrReceiverIdOrderByTimestampDesc(userId, userId);
        return messages.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void updateMessage(Message message) {
        messageRepository.save(message);
    }

    private MessageDto convertToDto(Message message) {
        MessageDto dto = new MessageDto();
        dto.setId(message.getId());
        dto.setSenderId(message.getSenderId());
        dto.setReceiverId(message.getReceiverId());
        dto.setContent(message.getContent());
        dto.setTimestamp(message.getTimestamp());
        dto.setStatus(message.getStatus());
        return dto;
    }
}
