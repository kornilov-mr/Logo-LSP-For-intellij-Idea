package org.example.server.handlers;

import org.example.communication.NoResponse;
import org.example.communication.notification.DidChangeConfigurationParams;

/**
 * Handler for responding to the DidChangeConfiguration notification in the Language Server Protocol (LSP).
 * <p>
 * This class extends the {@code LSPHandler} to handle {@code DidChangeConfigurationParams}, which represents
 * parameters sent when the client's configuration changes. The primary purpose of this handler is to
 * process these change events and log the occurrence of a configuration update.
 * <p>
 * Overrides the abstract {@code handle} method from the {@code LSPHandler} base class to implement the
 * processing logic specific to configuration change notifications.
 */
public class DidChangeConfigurationHandler extends LSPHandler<DidChangeConfigurationParams, NoResponse>{
    @Override
    protected NoResponse handle(DidChangeConfigurationParams didChangeConfigurationParams) throws Exception {
        this.logger.info("Configuration changed");
        return new NoResponse();
    }
}
