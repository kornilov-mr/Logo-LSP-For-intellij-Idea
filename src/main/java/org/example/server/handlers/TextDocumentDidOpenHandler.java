package org.example.server.handlers;

import org.example.communication.NoResponse;
import org.example.communication.notification.DidOpenTextDocumentParams;
import org.example.project.ProjectContext;

/**
 * Handler for the "didOpen" notification in the Language Server Protocol (LSP).
 *
 * This class is responsible for processing a notification that indicates a text document
 * has been opened in the client. It extends the {@code LSPHandler} class to implement
 * the specific behavior for handling {@code DidOpenTextDocumentParams} and produces
 * no response ({@code NoResponse}).
 *
 * Key responsibilities:
 * - Parses the {@code DidOpenTextDocumentParams}, which contain information about the
 *   opened text document, such as its URI and content.
 * - Calls the {@code ProjectContext.didOpenFile} method to update the server's
 *   internal state with the information about the opened file.
 *
 * Overrides the abstract {@code handle} method from the {@code LSPHandler} base class
 * to implement the notification-specific logic.
 *
 * @see LSPHandler
 * @see DidOpenTextDocumentParams
 * @see NoResponse
 */
public class TextDocumentDidOpenHandler extends LSPHandler<DidOpenTextDocumentParams, NoResponse>{
    @Override
    protected NoResponse handle(DidOpenTextDocumentParams didOpenTextDocumentParams) {
        ProjectContext.didOpenFile(didOpenTextDocumentParams.textDocument.documentURI,didOpenTextDocumentParams.textDocument.text);
        return new NoResponse();
    }
}
