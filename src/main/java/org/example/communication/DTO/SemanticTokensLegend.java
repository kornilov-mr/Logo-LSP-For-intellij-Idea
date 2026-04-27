package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;

public class SemanticTokensLegend extends LSPAny {

    @JsonProperty("tokenTypes")
    public String[] tokenTypes;

    @JsonProperty("tokenModifiers")
    public String[] tokenModifiers;

    @JsonCreator
    public SemanticTokensLegend(
            @JsonProperty("tokenTypes") String[] tokenTypes,
            @JsonProperty("tokenModifiers") String[] tokenModifiers) {
        this.tokenTypes = tokenTypes;
        this.tokenModifiers = tokenModifiers;
    }
}
