package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;

public class CompletionContext extends LSPAny {

    @JsonProperty("triggerKind")
    public CompletionTriggerKind triggerKind;

    @JsonProperty("triggerCharacter")
    public String triggerCharacter;

    @JsonCreator
    public CompletionContext(
            @JsonProperty("triggerKind") CompletionTriggerKind triggerKind,
            @JsonProperty("triggerCharacter") String triggerCharacter) {
        this.triggerKind = triggerKind;
        this.triggerCharacter = triggerCharacter;
    }
}
