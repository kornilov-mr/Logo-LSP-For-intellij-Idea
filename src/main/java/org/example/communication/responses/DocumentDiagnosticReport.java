package org.example.communication.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.DTO.Diagnostic;
import org.example.communication.LSPAny;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentDiagnosticReport extends LSPAny {
    @JsonProperty("kind")
    public String kind;

    @JsonProperty("resultId")
    public String resultId;

    @JsonProperty("items")
    public List<Diagnostic> items;

    public DocumentDiagnosticReport(List<Diagnostic> items) {
        this.kind = "full";
        this.items = items;
    }
}
