package com.mostafa.automation.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class readJson {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T readData(String dataUWant, Class<T> cls) throws IOException {

        File file = new File("src/main/resources/" + dataUWant + "Data.json");
        return objectMapper.readValue(file, cls);
    }
}
