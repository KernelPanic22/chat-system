package com.learning.chat.strategy.message.handling.impl;

import com.learning.chat.model.ChatMessage;
import com.learning.chat.model.enums.MessageType;
import com.learning.chat.strategy.message.handling.ChatMessageHandlingStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ChatChatMessageStrategy implements ChatMessageHandlingStrategy {

    @Override
    public ChatMessage handleMessage(ChatMessage message) {
        return message;
    }

    @Override
    public boolean supports(MessageType messageType) {
        return messageType == MessageType.CHAT;
    }
}
