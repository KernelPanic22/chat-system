package com.learning.chat.deserialization.impl;

import com.learning.chat.deserialization.DeserializationStrategy;
import com.learning.chat.deserialization.ReflectionCache;
import com.learning.chat.deserialization.annotation.FixedTag;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Component
@Order(1)
public class FixedTagDeserializationStrategy implements DeserializationStrategy {
    private final ReflectionCache reflectionCache;

    public FixedTagDeserializationStrategy(ReflectionCache reflectionCache) {
        this.reflectionCache = reflectionCache;
    }

    @Override
    public <T> T deserialize(String source, Class<T> targetClass, T instance) {
        List<Field> fields = reflectionCache.getAnnotatedFields(targetClass, FixedTag.class);

        for (Field field : fields) {
            if (field.isAnnotationPresent(FixedTag.class)) {
                FixedTag annotation = field.getAnnotation(FixedTag.class);
                String value = extractValue(source, annotation.tag(), annotation.delimiter());
                if (Objects.nonNull(value)) {
                    try {
                        // make accessible so we can set private fields
                        boolean accessible = field.canAccess(instance);
                        if (!accessible) {
                            field.setAccessible(true);
                        }
                        Object converted = convertValue(value, field.getType());
                        field.set(instance, converted);
                        if (!accessible) {
                            field.setAccessible(false);
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Failed to set field value", e);
                    }
                }
            }
        }

        return instance;
    }

    private String extractValue(String source, String tag, String delimiter) {
        String[] parts = source.split(Pattern.quote(delimiter));
        for (String part : parts) {
            if (part.startsWith(tag)) {
                return part.substring(tag.length());
            }
        }
        return null;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Object convertValue(String value, Class<?> targetType) {
        if (targetType == String.class) {
            return value;
        }
        if (targetType.isEnum()) {
            // enums expect exact name; adjust caller data or add mapping if needed
            return Enum.valueOf((Class<? extends Enum>) targetType, value);
        }
        if (targetType == Integer.class || targetType == int.class) {
            return Integer.valueOf(value);
        }
        if (targetType == Long.class || targetType == long.class) {
            return Long.valueOf(value);
        }
        if (targetType == Boolean.class || targetType == boolean.class) {
            return Boolean.valueOf(value);
        }
        if (targetType == Double.class || targetType == double.class) {
            return Double.valueOf(value);
        }
        // fallback: try to set raw string (may fail if types mismatch)
        return value;
    }
}
