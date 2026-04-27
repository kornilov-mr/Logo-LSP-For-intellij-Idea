package org.example.communication.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.DTO.TextDocumentIdentifier;
import org.example.communication.Message;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentDiagnosticParams extends Message {
    @JsonProperty("textDocument")
    public TextDocumentIdentifier textDocument;

    @JsonProperty("identifier")
    public String identifier;

    @JsonProperty("previousResultId")
    public String previousResultId;

    @JsonCreator
    public DocumentDiagnosticParams(
            @JsonProperty("textDocument") TextDocumentIdentifier textDocument,
            @JsonProperty("identifier") String identifier,
            @JsonProperty("previousResultId") String previousResultId) {
        this.textDocument = textDocument;
        this.identifier = identifier;
        this.previousResultId = previousResultId;
    }
}
