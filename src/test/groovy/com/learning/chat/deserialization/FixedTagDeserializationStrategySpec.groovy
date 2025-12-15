package com.learning.chat.deserialization


import com.learning.chat.deserialization.impl.FixedTagDeserializationStrategy
import com.learning.chat.deserialization.annotation.FixedTag
import spock.lang.Specification

import java.lang.reflect.Field

class FixedTagDeserializationStrategySpec extends Specification {

    def "extracts and sets annotated fields from source"() {
        given:
        ReflectionCache reflectionCache = Mock()
        def strategy = new FixedTagDeserializationStrategy(reflectionCache)
        def source = "C:hello|S:alice|"

        // small DTO with public fields so Field.set(...) won't throw IllegalAccessException


        def instance = new TestDto()
        Field f1 = TestDto.getDeclaredField("content")
        Field f2 = TestDto.getDeclaredField("sender")
        reflectionCache.getAnnotatedFields(TestDto, FixedTag) >> [f1, f2]

        when:
        def result = strategy.deserialize(source, TestDto, instance)

        then:
        result instanceof TestDto
        result.content == "hello"
        result.sender == "alice"
    }

    def "missing tag leaves field null"() {
        given:
        ReflectionCache reflectionCache = Mock()
        def strategy = new FixedTagDeserializationStrategy(reflectionCache)
        def source = "C:onlycontent|"


        def instance = new TestDto()
        Field f1 = TestDto.getDeclaredField("content")
        Field f2 = TestDto.getDeclaredField("sender")
        reflectionCache.getAnnotatedFields(TestDto, FixedTag) >> [f1, f2]

        when:
        def result = strategy.deserialize(source, TestDto, instance)

        then:
        result.content == "onlycontent"
        result.sender == null
    }

    class TestDto {
        @FixedTag(tag = "C:", delimiter = "|")
        public String content
        @FixedTag(tag = "S:", delimiter = "|")
        public String sender
    }
}

