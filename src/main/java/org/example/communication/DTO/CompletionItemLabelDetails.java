package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;

public class CompletionItemLabelDetails extends LSPAny {

    @JsonProperty("detail")
    public String detail;

    @JsonProperty("description")
    public String description;

    @JsonCreator
    public CompletionItemLabelDetails(
            @JsonProperty("detail") String detail,
            @JsonProperty("description") String description) {
        this.detail = detail;
        this.description = description;
    }
}
