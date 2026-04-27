package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkDoneProgressOptions extends LSPAny{
    @JsonProperty("workDoneProgress")
    public boolean workDoneProgress;

    @JsonCreator
    public WorkDoneProgressOptions(@JsonProperty("workDoneProgress") boolean workDoneProgress) {
        this.workDoneProgress = workDoneProgress;
    }
}
