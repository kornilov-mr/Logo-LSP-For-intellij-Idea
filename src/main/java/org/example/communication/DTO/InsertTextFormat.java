package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonValue;

public enum InsertTextFormat {
    PlainText(1),
    Snippet(2);

    private final int value;

    InsertTextFormat(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}
