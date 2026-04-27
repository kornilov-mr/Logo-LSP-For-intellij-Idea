package org.example.server.handlers;

import org.example.communication.LSPAny;

import java.util.logging.Logger;

/**
 * Base class for handling requests and responses in the Language Server Protocol (LSP).
 * <p>
 * This abstract class defines a framework for processing incoming LSP messages by:
 * 1. Parsing an LSP request of type {@code Request}.
 * 2. Delegating the processing of the request to a concrete implementation of the {@code handle} method.
 * 3. Returning the result as a response of type {@code Respond}.
 * <p>
 * Subclasses must provide an implementation of the {@code handle} method to specify
 * the behavior for processing the request type.
 *
 * @param <Request> The type of the request that this handler processes. Must extend {@link LSPAny}.
 * @param <Respond> The type of the response that this handler produces. Must extend {@link LSPAny}.
 */
public abstract class LSPHandler<Request extends LSPAny, Respond extends LSPAny> {
    protected final Logger logger = Logger.getLogger(this.getClass().getName());
    public LSPAny handleMessage(LSPAny message) throws Exception {
        return handle((Request) message);
    }
    protected abstract Respond handle(Request request)  throws Exception;
}
