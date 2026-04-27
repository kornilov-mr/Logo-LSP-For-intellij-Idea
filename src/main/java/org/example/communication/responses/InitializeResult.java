package org.example.communication.responses;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.DTO.ServerCapabilities;
import org.example.communication.LSPAny;

public class InitializeResult extends LSPAny {
    @JsonProperty("capabilities")
    public ServerCapabilities capabilities;
    @JsonProperty("serverInfo")
    @SuppressWarnings("unused")
    public ServerInfo serverInfo;

    public static class ServerInfo {
        @JsonProperty("name")
        public String name;
        @JsonProperty("version")
        public String version;

        @JsonCreator
        public ServerInfo(@JsonProperty("name") String name, @JsonProperty("version") String version) {
            this.name = name;
            this.version = version;
        }
    }
    @JsonCreator
    public InitializeResult(@JsonProperty("capabilities") ServerCapabilities capabilities, @JsonProperty("serverInfo") ServerInfo serverInfo) {
        this.capabilities = capabilities;
        this.serverInfo = serverInfo;
    }
}