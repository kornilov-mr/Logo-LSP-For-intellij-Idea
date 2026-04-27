package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;

public class TextDocumentIdentifier extends LSPAny {
    @JsonProperty("uri")
    public String uri;

    @JsonCreator
    public TextDocumentIdentifier(@JsonProperty("uri") String uri) {
        this.uri = uri;
    }
}
