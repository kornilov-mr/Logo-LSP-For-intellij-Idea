package org.example.communication.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;

import java.util.Objects;

public class HoverResult extends LSPAny {
    @JsonProperty("contents")
    public String value;

    @JsonCreator
    public HoverResult(@JsonProperty("contents") String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HoverResult that = (HoverResult) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
