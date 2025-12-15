package com.learning.chat.deserialization;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StructuredMapper {
    private final TypeFactory typeFactory;
    private final List<DeserializationStrategy> strategies;

    public StructuredMapper(TypeFactory typeFactory, List<DeserializationStrategy> strategies) {
        this.typeFactory = typeFactory;
        this.strategies = strategies;
    }


    public <T> T deserialize(String source, Class<T> targetClass) {
      T instance = typeFactory.createInstance(targetClass);
      strategies.forEach(
            strategy -> strategy.deserialize(source, targetClass, instance)
      );
        return instance;
    }
}
