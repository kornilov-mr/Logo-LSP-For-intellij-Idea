package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VersionedTextDocumentIdentifier extends TextDocumentIdentifier{

    @JsonProperty("version")
    public int version;
    @JsonCreator
    public VersionedTextDocumentIdentifier(@JsonProperty("uri") String uri, @JsonProperty("version") int version) {
        super(uri);
        this.version = version;
    }
}
