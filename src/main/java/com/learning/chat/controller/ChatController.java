package com.learning.chat.controller;

import com.learning.chat.deserialization.StructuredMapper;
import com.learning.chat.model.ChatMessage;
import com.learning.chat.strategy.message.handling.MessageHandlingFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final MessageHandlingFactory messageHandlingFactory;
    private final StructuredMapper mapper;

    public ChatController(MessageHandlingFactory messageHandlingFactory, StructuredMapper mapper) {
        this.messageHandlingFactory = messageHandlingFactory;
        this.mapper = mapper;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload String message) {
        ChatMessage chatMessage = mapper.deserialize(message, ChatMessage.class);
        return messageHandlingFactory.getStrategy(chatMessage.getType())
                .orElseThrow(() -> new IllegalArgumentException("Unsupported message type"))
                .handleMessage(chatMessage);
    }

}
