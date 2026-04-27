package org.example.communication.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.DTO.Position;
import org.example.communication.DTO.TextDocument;
import org.example.communication.Message;

import java.util.Objects;

public class HoverParams extends Message {
    @JsonProperty("textDocument")
    public TextDocument textDocument;
    @JsonProperty("position")
    public Position position;

    @JsonCreator
    public HoverParams(@JsonProperty("textDocument") TextDocument textDocument, @JsonProperty("position") Position position) {
        this.textDocument = textDocument;
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HoverParams that = (HoverParams) o;
        return Objects.equals(textDocument, that.textDocument) && Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textDocument, position);
    }
}
