package com.ultra.rmq.service;

import com.ultra.rmq.entity.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class MessageProducer {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;
    
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    public void sendMessage(Message message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
