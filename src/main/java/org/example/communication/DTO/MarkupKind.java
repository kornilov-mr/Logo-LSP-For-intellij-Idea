package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MarkupKind {
    PlainText("plaintext"),
    Markdown("markdown");

    private final String value;

    MarkupKind(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
