package com.learning.chat.strategy.message.handling.impl;

import com.learning.chat.factory.MessageFactory;
import com.learning.chat.model.RoomMessage;
import com.learning.chat.model.enums.MessageType;
import com.learning.chat.strategy.message.handling.RoomMessageHandlingStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class RoomMessageStrategy implements RoomMessageHandlingStrategy {


    private final MessageFactory messageFactory;

    @Override
    public RoomMessage handleMessage(RoomMessage roomMessage) {
        RoomMessage outboundRoomMessage = new RoomMessage();
        if (Objects.isNull(roomMessage.getRoomName()) && roomMessage.getRoomName().isEmpty()) {
            outboundRoomMessage.setMessageContent("Room name is required to create a room.");
            outboundRoomMessage.setType(MessageType.ERROR);
            return outboundRoomMessage;
        }


        return roomMessage;
    }

    @Override
    public boolean supports(MessageType messageType) {
        return messageType == MessageType.CREATE_ROOM;
    }
}
