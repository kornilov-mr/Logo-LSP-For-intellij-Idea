package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.communication.LSPAny;

public class CompletionItem extends LSPAny {

    @JsonProperty("label")
    public String label;

    @JsonProperty("labelDetails")
    public CompletionItemLabelDetails labelDetails;

    @JsonProperty("kind")
    public CompletionItemKind kind;

    @JsonProperty("tags")
    public CompletionItemTag[] tags;

    @JsonProperty("detail")
    public String detail;

    /** String or MarkupContent */
    @JsonProperty("documentation")
    public Object documentation;

    @JsonProperty("deprecated")
    public Boolean deprecated;

    @JsonProperty("preselect")
    public Boolean preselect;

    @JsonProperty("sortText")
    public String sortText;

    @JsonProperty("filterText")
    public String filterText;

    @JsonProperty("insertText")
    public String insertText;

    @JsonProperty("insertTextFormat")
    public InsertTextFormat insertTextFormat;

    @JsonProperty("insertTextMode")
    public InsertTextMode insertTextMode;

    /** TextEdit or InsertReplaceEdit */
    @JsonProperty("textEdit")
    public Object textEdit;

    @JsonProperty("additionalTextEdits")
    public TextEdit[] additionalTextEdits;

    @JsonProperty("commitCharacters")
    public String[] commitCharacters;

    @JsonProperty("command")
    public Command command;

    @JsonProperty("data")
    public LSPAny data;

    @JsonCreator
    public CompletionItem(@JsonProperty("label") String label) {
        this.label = label;
    }
}
