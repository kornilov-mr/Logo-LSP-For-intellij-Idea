package org.example.communication.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.Message;
import org.example.communication.DTO.TextDocumentIdentifier;

public class SemanticTokensParams extends Message {

    @JsonProperty("textDocument")
    public TextDocumentIdentifier textDocument;

    @JsonCreator
    public SemanticTokensParams(@JsonProperty("textDocument") TextDocumentIdentifier textDocument) {
        this.textDocument = textDocument;
    }
}
