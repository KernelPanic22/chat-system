package com.learning.chat.factory;

import com.learning.chat.model.ChatMessage;
import com.learning.chat.model.enums.MessageType;
import org.springframework.stereotype.Component;

@Component
public class MessageFactory {
    public ChatMessage createJoinMessage(String sender) {
        return ChatMessage.builder()
                .type(MessageType.JOIN)
                .sender(sender)
                .content(sender + " has joined the chat")
                .build();
    }

    public ChatMessage createLeaveMessage(String sender) {
        return ChatMessage.builder()
                .type(MessageType.LEAVE)
                .sender(sender)
                .content(sender + " has left the chat")
                .build();
    }

    public ChatMessage createChatMessage(String sender, String content) {
        return ChatMessage.builder()
                .type(MessageType.CHAT)
                .sender(sender)
                .content(content)
                .build();
    }

    public ChatMessage createSystemMessage(String content) {
        return ChatMessage.builder()
                .type(MessageType.SYSTEM)
                .sender("System")
                .content(content)
                .build();
    }
}
