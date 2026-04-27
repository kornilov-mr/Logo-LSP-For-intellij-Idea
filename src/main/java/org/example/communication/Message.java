package org.example.communication;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Message extends LSPAny {
    @JsonProperty("jsonrpc")
    public String jsonRPCVersion = "2.0";
}
