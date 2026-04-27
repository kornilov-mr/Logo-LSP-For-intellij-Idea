package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonValue;

public enum InsertTextMode {
    asIs(1),
    adjustIndentation(2);

    private final int value;

    InsertTextMode(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}
