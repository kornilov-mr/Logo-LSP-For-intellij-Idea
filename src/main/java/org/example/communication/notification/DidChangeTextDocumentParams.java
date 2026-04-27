package org.example.communication.notification;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.DTO.TextDocumentContentChangeEvent;
import org.example.communication.DTO.VersionedTextDocumentIdentifier;
import org.example.communication.LSPAny;

import java.util.List;

public class DidChangeTextDocumentParams extends LSPAny {
    @JsonProperty("textDocument")
    public VersionedTextDocumentIdentifier textDocument;
    @JsonProperty("contentChanges")
    public List<TextDocumentContentChangeEvent> contentChanges;
    @JsonCreator
    public DidChangeTextDocumentParams(@JsonProperty("textDocument") VersionedTextDocumentIdentifier textDocument, @JsonProperty("contentChanges") List<TextDocumentContentChangeEvent> contentChanges) {
        this.textDocument = textDocument;
        this.contentChanges = contentChanges;
    }
}
