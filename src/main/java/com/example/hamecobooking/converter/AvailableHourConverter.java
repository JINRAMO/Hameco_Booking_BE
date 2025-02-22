package com.example.hamecobooking.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.Map;

@Converter(autoApply = true)
public class AvailableHourConverter implements AttributeConverter<Map<String, int[]>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final TypeReference<Map<String, int[]>> typeReference = new TypeReference<>() {};

    @Override
    public String convertToDatabaseColumn(Map<String, int[]> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON 직렬화 실패", e);
        }
    }

    @Override
    public Map<String, int[]> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        try {
            return objectMapper.readValue(dbData, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("JSON 역직렬화 실패", e);
        }
    }
}