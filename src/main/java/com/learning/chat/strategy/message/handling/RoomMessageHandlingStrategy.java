package com.learning.chat.strategy.message.handling;

import com.learning.chat.model.RoomMessage;
import com.learning.chat.model.enums.MessageType;

public interface RoomMessageHandlingStrategy {
    RoomMessage handleMessage(RoomMessage roomMessage);
    boolean supports(MessageType messageType);
}
