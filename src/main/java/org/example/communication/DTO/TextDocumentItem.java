package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;

public class TextDocumentItem extends LSPAny {

    @JsonProperty("uri")
    public String documentURI;
    @JsonProperty("languageId")
    public String languageId;
    @JsonProperty("text")
    public String text;
    @JsonProperty("version")
    public int version;

    @JsonCreator
    public TextDocumentItem(@JsonProperty("uri") String documentURI,
                            @JsonProperty("languageId") String languageId,
                            @JsonProperty("text") String text,
                            @JsonProperty("version") int version) {
        this.documentURI = documentURI;
        this.languageId = languageId;
        this.text = text;
        this.version = version;
    }
}
