package org.example.communication.notification;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;

public class DidChangeConfigurationParams extends LSPAny {
    @JsonProperty("textDocument")
    public Object settings;

    @JsonCreator
    public DidChangeConfigurationParams(@JsonProperty("settings") Object settings) {
        this.settings = settings;
    }
}
