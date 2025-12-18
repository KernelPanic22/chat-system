package com.learning.chat.strategy.message.handling;

import com.learning.chat.model.enums.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RoomMessageHandlingFactory {

    private final Set<RoomMessageHandlingStrategy> strategies;

    public Optional<RoomMessageHandlingStrategy> getStrategy(MessageType messageType) {
        return strategies.stream()
                .filter(strategy -> strategy.supports(messageType))
                .findFirst();
    }
}
