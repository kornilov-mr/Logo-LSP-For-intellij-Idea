package org.example.server.handlers;

import org.example.communication.NoResponse;
import org.example.communication.notification.DidCloseTextDocumentParams;
import org.example.project.ProjectContext;

public class TextDocumentDidCloseHandler extends LSPHandler<DidCloseTextDocumentParams, NoResponse> {
    @Override
    protected NoResponse handle(DidCloseTextDocumentParams params) {
        ProjectContext.didCloseFile(params.textDocument.uri);
        return new NoResponse();
    }
}
