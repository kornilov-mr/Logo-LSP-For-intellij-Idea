package org.example.communication.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompletionOptions extends LSPAny{
    @JsonProperty("triggerCharacters")
    public String[] triggerCharacters;

    @JsonProperty("allCommitCharacters")
    public String[] allCommitCharacters;

    @JsonProperty("resolveProvider")
    public Boolean resolveProvider;

    @JsonProperty("completionItem")
    public CompletionItemOptions completionItem;

    public static class CompletionItemOptions {
        @JsonProperty("labelDetailsSupport")
        public Boolean labelDetailsSupport;

        public CompletionItemOptions(Boolean labelDetailsSupport) {
            this.labelDetailsSupport = labelDetailsSupport;
        }
    }

    public CompletionOptions(String[] triggerCharacters, String[] allCommitCharacters, Boolean resolveProvider, CompletionItemOptions completionItem) {
        this.triggerCharacters = triggerCharacters;
        this.allCommitCharacters = allCommitCharacters;
        this.resolveProvider = resolveProvider;
        this.completionItem = completionItem;
    }
}
