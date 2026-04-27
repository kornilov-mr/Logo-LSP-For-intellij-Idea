package org.example.communication.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.example.communication.LSPAny;

import java.io.IOException;

public class NullResult extends LSPAny {
    @JsonProperty("result")
    public Object result = null;
}
