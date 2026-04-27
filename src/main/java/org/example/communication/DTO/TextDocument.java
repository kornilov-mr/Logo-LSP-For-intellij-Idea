package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;

import java.util.Objects;

public class TextDocument extends LSPAny {
    @JsonProperty("uri")
    public String uri;

    @JsonCreator
    public TextDocument(@JsonProperty("uri") String uri) {
        this.uri = uri;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TextDocument that = (TextDocument) o;
        return Objects.equals(uri, that.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uri);
    }
}
