package com.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.model.VastModel;

public class JsonExporter {

    public static String exportToJson(VastModel vast) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(vast);
    }
}

