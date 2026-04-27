package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;

public class InsertReplaceEdit extends LSPAny {

    @JsonProperty("newText")
    public String newText;

    @JsonProperty("insert")
    public Range insert;

    @JsonProperty("replace")
    public Range replace;

    @JsonCreator
    public InsertReplaceEdit(
            @JsonProperty("newText") String newText,
            @JsonProperty("insert") Range insert,
            @JsonProperty("replace") Range replace) {
        this.newText = newText;
        this.insert = insert;
        this.replace = replace;
    }
}
