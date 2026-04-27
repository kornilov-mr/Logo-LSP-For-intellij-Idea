package org.example.server.handlers;

import org.example.communication.NoResponse;
import org.example.communication.notification.InitializedParams;

/**
 * Handler for responding to the Initialized notification in the Language Server Protocol (LSP).
 */
public class InitializedHandler extends LSPHandler<InitializedParams, NoResponse> {
    @Override
    protected NoResponse handle(InitializedParams initializedParams){
        this.logger.info("Initialized");
        return new NoResponse();
    }
}
