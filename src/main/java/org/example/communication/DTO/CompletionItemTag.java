package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CompletionItemTag {
    Deprecated(1);

    private final int value;

    CompletionItemTag(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}
