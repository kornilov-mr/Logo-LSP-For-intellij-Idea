package org.example.server.handlers;

import org.example.communication.requests.NoParameters;
import org.example.communication.responses.NullResult;
import org.example.server.WorkingContext;

/**
 * Handler for responding to the shutdown request in the Language Server Protocol (LSP).
 */
public class ShutDownHandler extends LSPHandler<NoParameters, NullResult>{
    @Override
    protected NullResult handle(NoParameters shutDownParams) throws Exception {
        this.logger.info("Shutting down waiting for exit request");
        WorkingContext.isShutdown = true;
        return new NullResult();
    }
}
