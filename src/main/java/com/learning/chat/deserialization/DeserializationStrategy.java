package com.learning.chat.deserialization;

public interface DeserializationStrategy {
    <T> T deserialize(String source, Class<T> targetClass, T instance);
}
