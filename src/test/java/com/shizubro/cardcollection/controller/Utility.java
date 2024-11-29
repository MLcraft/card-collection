package com.shizubro.cardcollection.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utility {
    // Helper method for testing to convert objects to JSON string
    public static String asJsonString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
