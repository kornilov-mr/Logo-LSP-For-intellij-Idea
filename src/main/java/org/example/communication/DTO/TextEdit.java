package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;

public class TextEdit extends LSPAny {

    @JsonProperty("range")
    public Range range;

    @JsonProperty("newText")
    public String newText;

    @JsonCreator
    public TextEdit(
            @JsonProperty("range") Range range,
            @JsonProperty("newText") String newText) {
        this.range = range;
        this.newText = newText;
    }
}
