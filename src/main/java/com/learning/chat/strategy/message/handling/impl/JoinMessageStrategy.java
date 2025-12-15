package com.learning.chat.strategy.message.handling.impl;

import com.learning.chat.factory.MessageFactory;
import com.learning.chat.model.ChatMessage;
import com.learning.chat.model.enums.MessageType;
import com.learning.chat.strategy.message.handling.MessageHandlingStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JoinMessageStrategy implements MessageHandlingStrategy {

    private final MessageFactory messageFactory;

    @Override
    public ChatMessage handleMessage(ChatMessage message) {
        return message;
    }

    @Override
    public boolean supports(MessageType messageType) {
        return messageType == MessageType.JOIN;
    }
}
