package com.learning.chat.strategy.message.handling.impl;

import com.learning.chat.model.RoomMessage;
import com.learning.chat.model.enums.MessageType;
import com.learning.chat.service.RoomService;
import com.learning.chat.strategy.message.handling.RoomMessageHandlingStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class RoomMessageStrategy implements RoomMessageHandlingStrategy {

    private final RoomService roomService;

    @Override
    public RoomMessage handleMessage(RoomMessage roomMessage) {
        // Validate room ID
        if (Objects.isNull(roomMessage.getRoomId()) || roomMessage.getRoomId().trim().isEmpty()) {
            log.warn("Room message failed: Room ID is required");
            return RoomMessage.builder()
                    .type(MessageType.ERROR)
                    .messageContent("Room ID is required to send a message.")
                    .build();
        }
        // Check if room exists
        if (!roomService.roomExists(roomMessage.getRoomId())) {
            log.warn("Room message failed: Room {} not found", roomMessage.getRoomId());
            return RoomMessage.builder()
                    .type(MessageType.ERROR)
                    .roomId(roomMessage.getRoomId())
                    .messageContent("Room not found.")
                    .build();
        }

        log.info("Broadcasting message to room {}: {}", roomMessage.getRoomId(), roomMessage.getMessageContent());
        
        // Return the message to be broadcast to room members
        return roomMessage;
    }

    @Override
    public boolean supports(MessageType messageType) {
        return messageType == MessageType.CHAT;
    }
}
