package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;

import java.util.Objects;

public class TextDocumentSyncOptions extends LSPAny {
    @JsonProperty("openClose")
    public boolean openClose;

    @JsonProperty("change")
    public int change;

    @JsonCreator
    public TextDocumentSyncOptions(@JsonProperty("openClose") boolean openClose,
                                   @JsonProperty("change") int change) {
        this.openClose = openClose;
        this.change = change;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TextDocumentSyncOptions that = (TextDocumentSyncOptions) o;
        return openClose == that.openClose && change == that.change;
    }

    @Override
    public int hashCode() {
        return Objects.hash(openClose, change);
    }
}
