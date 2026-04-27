package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;

public class SemanticTokensOptions extends LSPAny {

    @JsonProperty("legend")
    public SemanticTokensLegend legend;

    @JsonProperty("full")
    public boolean full;

    @JsonCreator
    public SemanticTokensOptions(
            @JsonProperty("legend") SemanticTokensLegend legend,
            @JsonProperty("full") boolean full) {
        this.legend = legend;
        this.full = full;
    }
}
