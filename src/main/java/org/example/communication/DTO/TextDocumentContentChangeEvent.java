package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;

public class TextDocumentContentChangeEvent extends LSPAny {
    @JsonProperty("range")
    public Range range;
    @JsonProperty("text")
    public String text;
    @JsonProperty("rangeLength")
    public int rangeLength;

    public TextDocumentContentChangeEvent(Range range, String text) {
        this.range = range;
        this.text = text;
        this.rangeLength = text.length();
    }

    @JsonCreator
    public TextDocumentContentChangeEvent(@JsonProperty("range") Range range, @JsonProperty("text") String text,
                                          @JsonProperty("rangeLength") int rangeLength) {
        this.range = range;
        this.text = text;
        this.rangeLength = rangeLength;
    }
}
