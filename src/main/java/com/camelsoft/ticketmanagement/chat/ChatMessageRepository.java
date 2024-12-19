package com.camelsoft.ticketmanagement.chat;

import com.camelsoft.ticketmanagement.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findBySenderAndRecipient(User sender, User recipient);
}