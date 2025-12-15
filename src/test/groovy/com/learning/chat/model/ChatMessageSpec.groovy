package com.learning.chat.model

import spock.lang.Specification

import com.learning.chat.model.enums.MessageType

class ChatMessageSpec extends Specification {

    def "setters and getters work"() {
        given:
        def msg = new ChatMessage()

        when:
        msg.setContent("hello")
        msg.setSender("alice")
        msg.setType(MessageType.CHAT)

        then:
        msg.getContent() == "hello"
        msg.getSender() == "alice"
        msg.getType() == MessageType.CHAT
    }

    def "builder creates correct ChatMessage"() {
        when:
        def built = ChatMessage.builder()
                .content("hi")
                .sender("bob")
                .type(MessageType.CHAT)
                .build()

        then:
        built.content == "hi"
        built.sender == "bob"
        built.type == MessageType.CHAT
    }
}

