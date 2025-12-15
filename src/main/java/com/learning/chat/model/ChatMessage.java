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
public class ChatMessage {
    @FixedTag(tag = MessageConstants.CONTENT_TAG, delimiter = MessageConstants.DELIMITER)
    private String content;
    @FixedTag(tag = MessageConstants.SENDER_TAG, delimiter = MessageConstants.DELIMITER)
    private String sender;
    @FixedTag(tag = MessageConstants.TYPE_TAG, delimiter = MessageConstants.DELIMITER)
    private MessageType type;
}
