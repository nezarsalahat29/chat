package com.ultra.rmq.repository;

import com.ultra.rmq.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderIdOrReceiverIdOrderByTimestampDesc(Long senderId, Long receiverId);
}
