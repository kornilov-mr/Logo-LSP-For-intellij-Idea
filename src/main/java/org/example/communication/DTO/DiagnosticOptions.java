package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;

public class DiagnosticOptions extends LSPAny {
    @JsonProperty("interFileDependencies")
    public boolean interFileDependencies;

    @JsonProperty("workspaceDiagnostics")
    public boolean workspaceDiagnostics;

    public DiagnosticOptions(boolean interFileDependencies, boolean workspaceDiagnostics) {
        this.interFileDependencies = interFileDependencies;
        this.workspaceDiagnostics = workspaceDiagnostics;
    }
}
