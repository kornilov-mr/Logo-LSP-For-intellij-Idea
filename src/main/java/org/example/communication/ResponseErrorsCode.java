package org.example.communication;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.NUMBER)
public enum ResponseErrorsCode {
    ParseError(-32700),
    InvalidRequest(-32600),
    MethodNotFound(-32601),
    InvalidParams(-32602),
    InternalError(-32603),
    jsonrpcReservedErrorRangeStart(-32099),
    ServerNotInitialized(-32002),
    UnknownErrorCode(-32001),
    lspReservedErrorRangeStart(-32899),
    RequestFailed(-32803),
    ServerCancelled(-32802),
    ContentModified(-32801),
    RequestCancelled(-32800),
    lspReservedErrorRangeEnd(-32800);

    @JsonValue
    public int getCode() {
        return errorCode;
    }
    @JsonProperty("errorCode")
    public final int errorCode;
    ResponseErrorsCode(int code) {
        this.errorCode = code;
    }
    @JsonCreator
    public static ResponseErrorsCode fromCode(int code) {
        for (ResponseErrorsCode s : ResponseErrorsCode.values()) {
            if (s.errorCode == code) {
                return s;
            }
        }
        throw new IllegalArgumentException("Unknown error code: " + code);
    }
}
