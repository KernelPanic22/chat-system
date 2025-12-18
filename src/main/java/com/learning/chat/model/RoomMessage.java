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
    @FixedTag(tag = MessageConstants.ROOM_NAME_TAG, delimiter = MessageConstants.DELIMITER)
    private String roomName;

    @FixedTag(tag = MessageConstants.ROOM_ID_TAG, delimiter = MessageConstants.DELIMITER)
    private String roomId;

    @FixedTag(tag = MessageConstants.TYPE_TAG, delimiter = MessageConstants.DELIMITER)
    private MessageType type;

    @FixedTag(tag = MessageConstants.MSG_CONTENT , delimiter = MessageConstants.DELIMITER)
    private String messageContent;
}
