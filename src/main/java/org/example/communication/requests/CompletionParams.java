package org.example.communication.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.Message;
import org.example.communication.DTO.CompletionContext;
import org.example.communication.DTO.Position;
import org.example.communication.DTO.TextDocumentIdentifier;

public class CompletionParams extends Message {

    @JsonProperty("textDocument")
    public TextDocumentIdentifier textDocument;

    @JsonProperty("position")
    public Position position;

    @JsonProperty("workDoneToken")
    public Object workDoneToken;

    @JsonProperty("partialResultToken")
    public Object partialResultToken;

    @JsonProperty("context")
    public CompletionContext context;

    @JsonCreator
    public CompletionParams(
            @JsonProperty("textDocument") TextDocumentIdentifier textDocument,
            @JsonProperty("position") Position position,
            @JsonProperty("workDoneToken") Object workDoneToken,
            @JsonProperty("partialResultToken") Object partialResultToken,
            @JsonProperty("context") CompletionContext context) {
        this.textDocument = textDocument;
        this.position = position;
        this.workDoneToken = workDoneToken;
        this.partialResultToken = partialResultToken;
        this.context = context;
    }
}
