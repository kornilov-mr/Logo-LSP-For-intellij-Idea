package org.example.communication.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.example.communication.LSPAny;
import org.example.communication.Message;

import java.util.Objects;

@JsonDeserialize(using = LSPWrapperDeserializer.class)
public class LSPRequestWrapper extends Message {

    @JsonProperty("id")
    public int id;
    @JsonProperty("method")
    public String methodName;
    @JsonProperty("params")
    public LSPAny params;

    @JsonCreator
    public LSPRequestWrapper(@JsonProperty("jsonrpc") String jsonRPCVersion,
                             @JsonProperty("id") int id,
                             @JsonProperty("method") String methodName,
                             @JsonProperty("params") LSPAny params) {
        this.jsonRPCVersion = jsonRPCVersion;
        this.id = id;
        this.methodName = methodName;
        this.params = params;
    }

    public LSPRequestWrapper(int id, String methodName, LSPAny params) {
        this("2.0",id,methodName,params);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LSPRequestWrapper that = (LSPRequestWrapper) o;
        return id == that.id && Objects.equals(jsonRPCVersion, that.jsonRPCVersion) && Objects.equals(methodName, that.methodName) && Objects.equals(params, that.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jsonRPCVersion, id, methodName, params);
    }
}
