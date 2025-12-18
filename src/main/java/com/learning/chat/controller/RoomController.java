package com.learning.chat.controller;

import com.learning.chat.deserialization.StructuredMapper;
import com.learning.chat.model.RoomMessage;
import com.learning.chat.strategy.message.handling.RoomMessageHandlingFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class RoomController {

    private final RoomMessageHandlingFactory roomMessageHandlingFactory;
    private final StructuredMapper mapper;

    public RoomController (RoomMessageHandlingFactory roomMessageHandlingFactory ,StructuredMapper mapper) {
        this.roomMessageHandlingFactory = roomMessageHandlingFactory;
        this.mapper = mapper;
    }

    @MessageMapping("/create.room")
    @SendTo("/topic/rooms")
    public RoomMessage createRoom(@Payload String message) {
        RoomMessage roomMessage = mapper.deserialize(message, RoomMessage.class);
        return roomMessageHandlingFactory.getStrategy(roomMessage.getType())
                .orElseThrow(() -> new IllegalArgumentException("Unsupported message type"))
                .handleMessage(roomMessage);
    }

    @MessageMapping("/join.room")
    @SendTo("/topic/rooms")
    public RoomMessage joinRoom(@Payload String message) {
        RoomMessage roomMessage = mapper.deserialize(message, RoomMessage.class);
        return roomMessageHandlingFactory.getStrategy(roomMessage.getType())
                .orElseThrow(() -> new IllegalArgumentException("Unsupported message type"))
                .handleMessage(roomMessage);
    }

}
