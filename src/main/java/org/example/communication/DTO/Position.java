package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;

import java.util.Objects;

public class Position extends LSPAny {
    @JsonProperty("line")
    public int line;
    @JsonProperty("character")
    public int character;

    @JsonCreator
    public Position(@JsonProperty("line") int line, @JsonProperty("character") int character) {
        this.line = line;
        this.character = character;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return line == position.line && character == position.character;
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, character);
    }

    @Override
    public String toString() {
        return "Position{" +
                "line=" + line +
                ", character=" + character +
                '}';
    }
}
