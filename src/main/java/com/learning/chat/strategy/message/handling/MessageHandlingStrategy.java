package com.learning.chat.strategy.message.handling;

import com.learning.chat.model.ChatMessage;
import com.learning.chat.model.enums.MessageType;

public interface MessageHandlingStrategy {
    ChatMessage handleMessage(ChatMessage message);
    boolean supports(MessageType messageType);
}
