package com.learning.chat.strategy.message.handling;

import com.learning.chat.model.enums.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MessageHandlingFactory {

    private final Set<MessageHandlingStrategy> strategies;

    public Optional<MessageHandlingStrategy> getStrategy(MessageType messageType) {
        return strategies.stream()
                .filter(strategy -> strategy.supports(messageType))
                .findFirst();
    }
}
