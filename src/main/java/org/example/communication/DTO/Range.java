package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;

public class Range extends LSPAny {
    @JsonProperty("start")
    public Position start;
    @JsonProperty("end")
    public Position end;
    @JsonCreator
    public Range(@JsonProperty("start") Position start, @JsonProperty("end") Position end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "Range{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
