package org.example.communication.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;
import org.example.communication.Message;
import org.example.communication.ResponseErrorsCode;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LSPResponseError extends LSPAny {
    @JsonProperty("code")
    public ResponseErrorsCode errorCode;
    @JsonProperty("message")
    public String message;
    @Nullable
    @JsonProperty("data")
    public Message data;

    public LSPResponseError(ResponseErrorsCode errorCode, String message, @Nullable Message data) {
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }

    public LSPResponseError(ResponseErrorsCode errorCode, String message) {
        this(errorCode,message,null);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LSPResponseError that = (LSPResponseError) o;
        return errorCode == that.errorCode && Objects.equals(message, that.message) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorCode, message, data);
    }
}
