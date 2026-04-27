package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Diagnostic extends LSPAny {
    @JsonProperty("range")
    public Range range;

    @JsonProperty("severity")
    public Integer severity;

    @JsonProperty("source")
    public String source;

    @JsonProperty("message")
    public String message;

    public Diagnostic(Range range, Integer severity, String source, String message) {
        this.range = range;
        this.severity = severity;
        this.source = source;
        this.message = message;
    }
}
