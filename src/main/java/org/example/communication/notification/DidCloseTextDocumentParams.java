package org.example.communication.notification;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.DTO.TextDocumentIdentifier;
import org.example.communication.LSPAny;

public class DidCloseTextDocumentParams extends LSPAny {
    @JsonProperty("textDocument")
    public TextDocumentIdentifier textDocument;

    @JsonCreator
    public DidCloseTextDocumentParams(@JsonProperty("textDocument") TextDocumentIdentifier textDocument) {
        this.textDocument = textDocument;
    }
}
