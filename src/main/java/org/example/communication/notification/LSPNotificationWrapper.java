package org.example.communication.notification;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;
import org.example.communication.Message;

import java.util.Objects;

public class LSPNotificationWrapper extends Message {
    @JsonProperty("jsonrpc")
    public String jsonRPCVersion;
    @JsonProperty("method")
    public String methodName;
    @JsonProperty("params")
    public LSPAny params;

    @JsonCreator
    public LSPNotificationWrapper(@JsonProperty("jsonrpc") String jsonRPCVersion,
                             @JsonProperty("method") String methodName,
                             @JsonProperty("params") LSPAny params) {
        this.jsonRPCVersion = jsonRPCVersion;
        this.methodName = methodName;
        this.params = params;
    }

    public LSPNotificationWrapper(String methodName, LSPAny params) {
        this("2.0",methodName,params);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LSPNotificationWrapper that = (LSPNotificationWrapper) o;
        return Objects.equals(jsonRPCVersion, that.jsonRPCVersion) && Objects.equals(methodName, that.methodName) && Objects.equals(params, that.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jsonRPCVersion, methodName, params);
    }
}
