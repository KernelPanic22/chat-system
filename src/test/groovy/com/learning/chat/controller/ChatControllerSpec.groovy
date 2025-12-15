package com.learning.chat.controller

import com.learning.chat.deserialization.StructuredMapper
import com.learning.chat.model.ChatMessage
import com.learning.chat.model.enums.MessageType
import com.learning.chat.strategy.message.handling.MessageHandlingFactory
import com.learning.chat.strategy.message.handling.MessageHandlingStrategy
import org.junit.jupiter.api.Test
import org.spockframework.spring.SpringBean
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = [ChatController, MessageHandlingFactory, StructuredMapper])
class ChatControllerSpec extends Specification {

    @SpringBean
    MessageHandlingFactory messageHandlingFactory = Stub()
    @SpringBean
    StructuredMapper mapper = Stub()
    @SpringBean
    ChatController controller = Stub()

    def setup() {
        controller = new ChatController(messageHandlingFactory, mapper)
    }


    def "sendMessage deserializes and delegates to correct strategy"() {
        given:
        def rawMessage = "C:hello|S:alice|T:CHAT|"
        def chatMessage = ChatMessage.builder()
                .content("hello")
                .sender("alice")
                .type(MessageType.CHAT)
                .build()
        def strategy = Mock(MessageHandlingStrategy)
        def handledMessage = ChatMessage.builder()
                .content("hello")
                .sender("alice")
                .type(MessageType.CHAT)
                .build()

        mapper.deserialize(_,_) >> chatMessage
        messageHandlingFactory.getStrategy(MessageType.CHAT) >> Optional.of(strategy)
        strategy.handleMessage(chatMessage) >> handledMessage

        when:
        def result = controller.sendMessage(rawMessage)

        then:
        result == handledMessage
    }

    def "sendMessage throws exception when no strategy found"() {
        given:
        def rawMessage = "C:test|S:bob|T:LEAVE|"
        def chatMessage = ChatMessage.builder()
                .content("test")
                .sender("bob")
                .type(MessageType.LEAVE)
                .build()

        mapper.deserialize(rawMessage, _ as Class) >> chatMessage
        messageHandlingFactory.getStrategy(MessageType.LEAVE) >> Optional.empty()

        when:
        controller.sendMessage(rawMessage)

        then:
        thrown(IllegalArgumentException)
        1 * mapper.deserialize(rawMessage, _ as Class)
        1 * messageHandlingFactory.getStrategy(MessageType.LEAVE)
    }

    def "sendMessage handles JOIN message type"() {
        given:
        def rawMessage = "S:charlie|T:JOIN|"
        def joinMessage = ChatMessage.builder()
                .sender("charlie")
                .type(MessageType.JOIN)
                .build()
        def strategy = Mock(MessageHandlingStrategy)

        mapper.deserialize(rawMessage, _ as Class) >> joinMessage
        messageHandlingFactory.getStrategy(MessageType.JOIN) >> Optional.of(strategy)
        strategy.handleMessage(joinMessage) >> joinMessage

        when:
        def result = controller.sendMessage(rawMessage)

        then:
        result == joinMessage
        1 * messageHandlingFactory.getStrategy(MessageType.JOIN)
        1 * strategy.handleMessage(joinMessage)
    }
}
