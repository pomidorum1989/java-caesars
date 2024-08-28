package io.dorum.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ApiHelper {
    public static <T> T convertJsonStringToDTO(String jsonString, Class<T> dtoClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonString, dtoClass);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert JSON string to DTO", e);
        }
    }

    public static <T> List<T> convertJsonStringArrayToDTO(String jsonArray, Class<T> dtoClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonArray, objectMapper.getTypeFactory().constructCollectionType(List.class, dtoClass));
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert JSON string array to DTO", e);
        }
    }

    public static String readJsonFromFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
