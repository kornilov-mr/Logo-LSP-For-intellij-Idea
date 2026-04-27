package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CompletionTriggerKind {
    Invoked(1),
    TriggerCharacter(2),
    TriggerForIncompleteCompletions(3);

    private final int value;

    CompletionTriggerKind(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}
