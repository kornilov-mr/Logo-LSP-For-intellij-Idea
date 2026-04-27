package org.example.server.handlers;

import org.example.communication.DTO.CompletionItem;

/**
 * A handler for resolving completion items in the Language Server Protocol (LSP).
 * <p>
 * This class extends the {@code LSPHandler} to specifically handle
 * {@code CompletionItem} objects, allowing for additional context or data to
 * be resolved for a given completion item during the completion process.
 * <p>
 * The primary role of this handler is to receive a {@code CompletionItem},
 * perform any necessary resolution steps (if applicable), and return the
 * resolved {@code CompletionItem}.
 * <p>
 * Overrides the abstract {@code handle} method from the {@code LSPHandler}
 * base class to provide the specific implementation for processing
 * {@code CompletionItem} inputs.
 */
public class CompletionItemResolveHandler extends LSPHandler<CompletionItem, CompletionItem> {

    @Override
    protected CompletionItem handle(CompletionItem item) {
        return item;
    }
}
