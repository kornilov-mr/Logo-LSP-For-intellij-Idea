package org.example.communication.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.Message;
import org.example.communication.DTO.Position;
import org.example.communication.DTO.TextDocumentIdentifier;

public class DeclarationParams extends Message {

    @JsonProperty("textDocument")
    public TextDocumentIdentifier textDocument;

    @JsonProperty("position")
    public Position position;

    @JsonCreator
    public DeclarationParams(
            @JsonProperty("textDocument") TextDocumentIdentifier textDocument,
            @JsonProperty("position") Position position) {
        this.textDocument = textDocument;
        this.position = position;
    }
}
