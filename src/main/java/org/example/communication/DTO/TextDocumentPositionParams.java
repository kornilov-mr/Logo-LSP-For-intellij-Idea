package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;

public class TextDocumentPositionParams extends LSPAny {

    @JsonProperty("textDocument")
    public TextDocumentIdentifier textDocument;
    @JsonProperty("position")
    public Position position;

    @JsonCreator
    public TextDocumentPositionParams(@JsonProperty("textDocument") TextDocumentIdentifier textDocument, @JsonProperty("position") Position position) {
        this.textDocument = textDocument;
        this.position = position;
    }
}
