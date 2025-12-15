package com.learning.chat.deserialization;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ReflectionCache {
    private final Map<CacheKey, List<Field>> fieldCache = new ConcurrentHashMap<>();

    public <T extends Annotation> List<Field> getAnnotatedFields(Class<?> clazz, Class<T> annotationClass) {

        if (fieldCache.size() > 1000) {
            fieldCache.clear();
        }

        CacheKey key = new CacheKey(clazz, annotationClass);
        return fieldCache.computeIfAbsent(key, k ->
            getAllFields(clazz).stream()
                    .filter(field -> field.isAnnotationPresent(annotationClass))
                    .peek(field -> field.setAccessible(Boolean.TRUE))
                    .collect(Collectors.toList()));
    }

    private static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        Class<?> current = clazz;

        while (current != null && current != Object.class) {
            fields.addAll(Arrays.asList(current.getDeclaredFields()));
            current = current.getSuperclass();
        }

        return fields;
    }


    private record CacheKey(Class<?> clazz, Class<? extends Annotation> annotationClass) { }
}
