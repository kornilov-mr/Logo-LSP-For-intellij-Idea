package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;

public class SemanticTokens extends LSPAny {

    @JsonProperty("resultId")
    public String resultId;

    @JsonProperty("data")
    public int[] data;

    @JsonCreator
    public SemanticTokens(
            @JsonProperty("resultId") String resultId,
            @JsonProperty("data") int[] data) {
        this.resultId = resultId;
        this.data = data;
    }
}
