package org.example.communication.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.DTO.ClientInfo;
import org.example.communication.LSPAny;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InitializeParams extends LSPAny {
    @JsonProperty("processId")
    public Integer processId;

    @JsonProperty("clientInfo")
    public ClientInfo clientInfo;

    @Nullable
    @JsonProperty("locale")
    public String locale;

    @Nullable
    @JsonProperty("rootPath")
    public String rootPath;

    @JsonProperty("rootUri")
    public String rootUri;

    @Nullable
    @JsonProperty("initializationOptions")
    public Object initializationOptions;

    @JsonProperty("capabilities")
    public ClientCapabilities capabilities;

    @Nullable
    @JsonProperty("trace")
    public String trace;



    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ClientCapabilities {

    }

    @Override
    public String toString() {
        return "InitializeParams{" +
                "processId=" + processId +
                ", clientInfo=" + clientInfo +
                ", locale='" + locale + '\'' +
                ", rootPath='" + rootPath + '\'' +
                ", rootUri='" + rootUri + '\'' +
                ", initializationOptions=" + initializationOptions +
                ", capabilities=" + capabilities +
                ", trace='" + trace + '\'' +
                '}';
    }
}
