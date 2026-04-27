package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;
import org.jetbrains.annotations.Nullable;

public class ClientInfo extends LSPAny {
    @JsonProperty("name")
    public String name;
    @Nullable
    @JsonProperty("version")
    public String version;

    public ClientInfo(@JsonProperty("name") String name, @JsonProperty("version") @Nullable String version) {
        this.name = name;
        this.version = version;
    }
}
