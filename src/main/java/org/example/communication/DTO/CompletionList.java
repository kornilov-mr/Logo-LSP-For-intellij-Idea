package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;

public class CompletionList extends LSPAny {

    @JsonProperty("isIncomplete")
    public boolean isIncomplete;

    @JsonProperty("itemDefaults")
    public ItemDefaults itemDefaults;

    @JsonProperty("items")
    public CompletionItem[] items;

    @JsonCreator
    public CompletionList(
            @JsonProperty("isIncomplete") boolean isIncomplete,
            @JsonProperty("itemDefaults") ItemDefaults itemDefaults,
            @JsonProperty("items") CompletionItem[] items) {
        this.isIncomplete = isIncomplete;
        this.itemDefaults = itemDefaults;
        this.items = items;
    }

    public static class ItemDefaults extends LSPAny {

        @JsonProperty("commitCharacters")
        public String[] commitCharacters;

        /** Range or {insert: Range, replace: Range} */
        @JsonProperty("editRange")
        public Object editRange;

        @JsonProperty("insertTextFormat")
        public InsertTextFormat insertTextFormat;

        @JsonProperty("insertTextMode")
        public InsertTextMode insertTextMode;

        @JsonProperty("data")
        public LSPAny data;

        @JsonCreator
        public ItemDefaults(
                @JsonProperty("commitCharacters") String[] commitCharacters,
                @JsonProperty("editRange") Object editRange,
                @JsonProperty("insertTextFormat") InsertTextFormat insertTextFormat,
                @JsonProperty("insertTextMode") InsertTextMode insertTextMode,
                @JsonProperty("data") LSPAny data) {
            this.commitCharacters = commitCharacters;
            this.editRange = editRange;
            this.insertTextFormat = insertTextFormat;
            this.insertTextMode = insertTextMode;
            this.data = data;
        }
    }
}
