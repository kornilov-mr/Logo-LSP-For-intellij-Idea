package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;

public class MarkupContent extends LSPAny {

    @JsonProperty("kind")
    public MarkupKind kind;

    @JsonProperty("value")
    public String value;

    @JsonCreator
    public MarkupContent(
            @JsonProperty("kind") MarkupKind kind,
            @JsonProperty("value") String value) {
        this.kind = kind;
        this.value = value;
    }
}
