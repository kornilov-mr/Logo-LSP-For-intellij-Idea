package org.example.communication.notification;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.DTO.TextDocumentItem;
import org.example.communication.LSPAny;

public class DidOpenTextDocumentParams extends LSPAny {
    @JsonProperty("textDocument")
    public TextDocumentItem textDocument;

    @JsonCreator
    public DidOpenTextDocumentParams(@JsonProperty("textDocument") TextDocumentItem textDocument) {
        this.textDocument = textDocument;
    }
}
