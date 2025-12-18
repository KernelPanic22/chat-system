package com.learning.chat.model;

import com.learning.chat.deserialization.annotation.FixedTag;
import com.learning.chat.model.enums.MessageType;
import com.learning.chat.strategy.message.constant.MessageConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomMessage {

    private String sender;
    private String roomName;
    private String roomId;
    private MessageType type;
    private String messageContent;
}
