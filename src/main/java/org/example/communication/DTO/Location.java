package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;

public class Location extends LSPAny {

    @JsonProperty("uri")
    public String uri;

    @JsonProperty("range")
    public Range range;

    @JsonCreator
    public Location(
            @JsonProperty("uri") String uri,
            @JsonProperty("range") Range range) {
        this.uri = uri;
        this.range = range;
    }
}
