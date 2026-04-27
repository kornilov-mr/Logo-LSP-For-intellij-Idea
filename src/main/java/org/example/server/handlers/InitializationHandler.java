package org.example.server.handlers;

import org.example.communication.requests.InitializeParams;
import org.example.communication.responses.InitializeResult;
import org.example.utils.LspServerInfo;


/**
 * Handles the initialization process for the Language Server Protocol (LSP).
 * <p>
 * This class is responsible for processing incoming `InitializeParams` and
 * producing an `InitializeResult` containing the server's capabilities and
 * server information. It extends the abstract `LSPHandler` class to implement
 * the specific behavior for the initialization phase of an LSP session.
 * <p>
 * Key responsibilities:
 * - Parse and log the initialization parameters received from the client.
 * - Generate and return an instance of `InitializeResult` that includes
 *   the server's capabilities and metadata about the server.
 * <p>
 * Overrides the abstract `handle` method from the base `LSPHandler` class
 * to implement initialization-specific behavior.
 */
public class InitializationHandler extends LSPHandler<InitializeParams, InitializeResult> {

    @Override
    protected InitializeResult handle(InitializeParams initializeParams) {
        this.logger.info("Initialization with params:" + initializeParams.toString());
        return new InitializeResult(
                LspServerInfo.getDefaultCapabilities(),
                LspServerInfo.getServerInfo());
    }
}
