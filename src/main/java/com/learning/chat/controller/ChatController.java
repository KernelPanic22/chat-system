package com.learning.chat.controller;

import com.learning.chat.deserialization.StructuredMapper;
import com.learning.chat.model.ChatMessage;
import com.learning.chat.strategy.message.handling.ChatMessageHandlingFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final ChatMessageHandlingFactory chatMessageHandlingFactory;
    private final StructuredMapper mapper;

    public ChatController(ChatMessageHandlingFactory chatMessageHandlingFactory, StructuredMapper mapper) {
        this.chatMessageHandlingFactory = chatMessageHandlingFactory;
        this.mapper = mapper;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage message) {
        return chatMessageHandlingFactory.getStrategy(message.getType())
                .orElseThrow(() -> new IllegalArgumentException("Unsupported message type"))
                .handleMessage(message);
    }

}
