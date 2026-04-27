package org.example.server.handlers;

import org.example.communication.NoResponse;
import org.example.communication.requests.NoParameters;
import org.example.server.WorkingContext;

/**
 * Handler for responding to the exit notification in the Language Server Protocol (LSP).
 * <p>
 * This class extends the {@code LSPHandler} to handle requests with no parameters
 * ({@code NoParameters}) and provides no response ({@code NoResponse}). It is responsible
 * for performing the final exit operation of the server by stopping the process and
 * exiting with the appropriate exit code.
 * <p>
 * The exit code depends on the {@code isShutdown} flag in the {@code WorkingContext}:
 * - If {@code isShutdown} is true, the process exits with a code of 0 (normal termination).
 * - If {@code isShutdown} is false, the process exits with a code of 1 (abnormal termination).
 * <p>
 * Overrides the abstract {@code handle} method from the {@code LSPHandler} base class to
 * implement the process exit behavior.
 */
public class ExitHandler extends LSPHandler<NoParameters, NoResponse>{
    @Override
    protected NoResponse handle(NoParameters noParameters) throws Exception {
        this.logger.info("Exiting");
        System.exit(WorkingContext.isShutdown ? 0 : 1);
        return new NoResponse();
    }
}
