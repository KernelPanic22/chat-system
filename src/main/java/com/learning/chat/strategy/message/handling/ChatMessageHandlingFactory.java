package com.learning.chat.strategy.message.handling;

import com.learning.chat.model.enums.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ChatMessageHandlingFactory {

    private final Set<ChatMessageHandlingStrategy> strategies;

    public Optional<ChatMessageHandlingStrategy> getStrategy(MessageType messageType) {
        return strategies.stream()
                .filter(strategy -> strategy.supports(messageType))
                .findFirst();
    }
}
