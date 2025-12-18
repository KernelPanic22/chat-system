package com.learning.chat.model.enums;

public enum MessageType {
    JOIN("User joined"),
    CHAT("Chat message"),
    SYSTEM("System message"),
    CREATE_ROOM("Create room"),
    JOIN_ROOM("Join room"),
    ERROR("Error message"),
    LEAVE("User left");

    private final String description;

    MessageType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
