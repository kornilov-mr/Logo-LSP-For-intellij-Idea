package org.example.communication.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;
import org.example.communication.Message;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class LSPResponseWrapper extends Message {
    @JsonProperty("jsonrpc")
    private String jsonRPCVersion = "2.0";

    @JsonProperty("id")
    public int id;
    @JsonProperty("result")
    public LSPAny result;
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("error")
    public LSPResponseError error;

    @JsonCreator
    public LSPResponseWrapper(@JsonProperty("id") int id,
                                   @JsonProperty("result") LSPAny result,
                                   @JsonProperty("error") @Nullable LSPResponseError error) {
        this.id = id;
        this.result = result;
        this.error = error;
    }

    public LSPResponseWrapper(int id, LSPAny result) {
        this(id,result,null);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LSPResponseWrapper that = (LSPResponseWrapper) o;
        return id == that.id && Objects.equals(jsonRPCVersion, that.jsonRPCVersion) && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jsonRPCVersion, id, result);
    }
}
