package org.example.server.handlers;

import org.example.communication.DTO.TextDocumentContentChangeEvent;
import org.example.communication.NoResponse;
import org.example.communication.notification.DidChangeTextDocumentParams;
import org.example.project.FileNode;
import org.example.project.ProjectContext;

import java.util.List;

/**
 * Handler for processing text document change notifications in a Language Server Protocol (LSP) implementation.
 *
 * This class listens for "didChange" events, which are triggered when the contents
 * of a text document are modified. The handler performs the following operations:
 *
 * 1. Retrieves the text document associated with the event using the document's URI from the {@link ProjectContext}.
 * 2. Applies the content changes specified in the {@code DidChangeTextDocumentParams} object to the corresponding file.
 * 3. Updates the in-memory Abstract Syntax Tree (AST) representation of the document after the changes have been applied.
 * 4. Returns a {@link NoResponse} object, indicating that no further action is required.
 *
 * This handler ensures that changes to a document are reflected in its content and parsed state within the
 * language server's runtime context.
 *
 * Extends:
 * {@link LSPHandler} with {@code DidChangeTextDocumentParams} as the request type and {@code NoResponse} as the response type.
 */
public class TextDocumentDidChangeHandler extends LSPHandler<DidChangeTextDocumentParams, NoResponse>{
    @Override
    protected NoResponse handle(DidChangeTextDocumentParams didChangeTextDocumentParams) {
        List<TextDocumentContentChangeEvent> changes = didChangeTextDocumentParams.contentChanges;
        FileNode currFile = ProjectContext.getFileNode(didChangeTextDocumentParams.textDocument.uri);
        currFile.applyChanges(changes);
        currFile.processNode();
        return new NoResponse();
    }
}
