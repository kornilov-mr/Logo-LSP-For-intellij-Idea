package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;

public class Command extends LSPAny {

    @JsonProperty("title")
    public String title;

    @JsonProperty("command")
    public String command;

    @JsonProperty("arguments")
    public LSPAny[] arguments;

    @JsonCreator
    public Command(
            @JsonProperty("title") String title,
            @JsonProperty("command") String command,
            @JsonProperty("arguments") LSPAny[] arguments) {
        this.title = title;
        this.command = command;
        this.arguments = arguments;
    }
}
