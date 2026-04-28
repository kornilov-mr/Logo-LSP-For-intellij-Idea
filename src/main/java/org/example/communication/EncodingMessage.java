package org.example.communication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.communication.requests.LSPRequestWrapper;

import java.nio.charset.StandardCharsets;

public class EncodingMessage {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String encodeToJsonRPC(LSPAny message) {
        String json = message.toJson();
        return String.format("Content-Length:%s\r\n\r\n%s", json.getBytes(StandardCharsets.UTF_8).length, json);
    }

    public static Message decodeFromJsonRPC(String jsonRPC) {
        String[] parts = jsonRPC.split("\r\n\r\n", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid JSON-RPC format");
        }
        String header = parts[0];
        String json = parts[1];
        String[] headerParts = header.split(":");
        if (headerParts.length < 2 || !headerParts[0].equals("Content-Length")) {
            throw new IllegalArgumentException("Invalid Content-Length header");
        }
        int contentLength = Integer.parseInt(headerParts[1]);
        if (json.getBytes(StandardCharsets.UTF_8).length != contentLength) {
            throw new IllegalArgumentException("Content length mismatch");
        }
        try {
            return objectMapper.readValue(json, LSPRequestWrapper.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
