package com.learning.chat.deserialization;

import org.springframework.stereotype.Component;

@Component
public class TypeFactory {
    public <T> T createInstance(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance of " + clazz.getName(), e);
        }
    }
}
