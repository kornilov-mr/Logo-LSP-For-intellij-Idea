package handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.communication.requests.HoverParams;
import org.example.communication.responses.HoverResult;
import org.example.server.handlers.TextDocumentHoverHandler;
import org.example.project.ProjectContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class HoverProviderTests {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final TextDocumentHoverHandler hoverHandler = new TextDocumentHoverHandler();
    private static final String URI = "src/test/resources/ast/programs/forSemanticTokens/shrinkingSquares.logo";

    @BeforeAll
    public static void setup() throws IOException {
        ProjectContext.didOpenFile(URI, Files.readString(new File(URI).toPath()));
    }

    @Test
    public void testHoverOverParameter() throws Exception {
        // ":size" in "to drawSquare :size" — line 0, col 16 lands inside ParameterNode
        String expected = "{\"contents\":\"Param(size)\"}";
        String requestParams = "{\"textDocument\":{\"uri\":\"" + URI + "\"},\"position\":{\"line\":0,\"character\":16}}";
        HoverParams params = objectMapper.readValue(requestParams, HoverParams.class);

        HoverResult result = (HoverResult) hoverHandler.handleMessage(params);
        Assertions.assertEquals(expected, objectMapper.writeValueAsString(result));
    }

    @Test
    public void testHoverOverFunctionCall() throws Exception {
        // "forward" on line 2, col 10 — inside the CallNode, before the :size argument
        String expected = "{\"contents\":\"\\nCall(forward, [Var(size)])\"}";
        String requestParams = "{\"textDocument\":{\"uri\":\"" + URI + "\"},\"position\":{\"line\":2,\"character\":10}}";
        HoverParams params = objectMapper.readValue(requestParams, HoverParams.class);

        HoverResult result = (HoverResult) hoverHandler.handleMessage(params);
        Assertions.assertEquals(expected, objectMapper.writeValueAsString(result));
    }

    @Test
    public void testHoverOverVariableReference() throws Exception {
        // ":size" argument to forward on line 2, col 17 — VariableRefNode wins over CallNode
        String expected = "{\"contents\":\"Var(size)\"}";
        String requestParams = "{\"textDocument\":{\"uri\":\"" + URI + "\"},\"position\":{\"line\":2,\"character\":17}}";
        HoverParams params = objectMapper.readValue(requestParams, HoverParams.class);

        HoverResult result = (HoverResult) hoverHandler.handleMessage(params);
        Assertions.assertEquals(expected, objectMapper.writeValueAsString(result));
    }

    @Test
    public void testHoverOverCallWithNumberArg() throws Exception {
        // "right" on line 3, col 10 — inside CallNode(right, [90])
        String expected = "{\"contents\":\"\\nCall(right, [90])\"}";
        String requestParams = "{\"textDocument\":{\"uri\":\"" + URI + "\"},\"position\":{\"line\":3,\"character\":10}}";
        HoverParams params = objectMapper.readValue(requestParams, HoverParams.class);

        HoverResult result = (HoverResult) hoverHandler.handleMessage(params);
        Assertions.assertEquals(expected, objectMapper.writeValueAsString(result));
    }

    @Test
    public void testHoverOverNumberLiteral() throws Exception {
        // "90" on line 3, col 15 — NumberNode wins over CallNode(right)
        String expected = "{\"contents\":\"90\"}";
        String requestParams = "{\"textDocument\":{\"uri\":\"" + URI + "\"},\"position\":{\"line\":3,\"character\":15}}";
        HoverParams params = objectMapper.readValue(requestParams, HoverParams.class);

        HoverResult result = (HoverResult) hoverHandler.handleMessage(params);
        Assertions.assertEquals(expected, objectMapper.writeValueAsString(result));
    }

    @Test
    public void testHoverOverUserDefinedFunctionCall() throws Exception {
        // "drawSquare" on line 7, col 8 — inside CallNode(drawSquare, [:size])
        String expected = "{\"contents\":\"\\nCall(drawSquare, [Var(size)])\"}";
        String requestParams = "{\"textDocument\":{\"uri\":\"" + URI + "\"},\"position\":{\"line\":7,\"character\":8}}";
        HoverParams params = objectMapper.readValue(requestParams, HoverParams.class);

        HoverResult result = (HoverResult) hoverHandler.handleMessage(params);
        Assertions.assertEquals(expected, objectMapper.writeValueAsString(result));
    }

    @Test
    public void testHoverOverMakeDeclaration() throws Exception {
        // "make" on line 13, col 2 — VariableDeclaration; NumberNode(100) starts at col 11 so doesn't compete
        String expected = "{\"contents\":\"\\nDeclare(size, 100)\"}";
        String requestParams = "{\"textDocument\":{\"uri\":\"" + URI + "\"},\"position\":{\"line\":13,\"character\":2}}";
        HoverParams params = objectMapper.readValue(requestParams, HoverParams.class);

        HoverResult result = (HoverResult) hoverHandler.handleMessage(params);
        Assertions.assertEquals(expected, objectMapper.writeValueAsString(result));
    }

    @Test
    public void testHoverOverNumberLiteralInMake() throws Exception {
        // "100" on line 13, col 12 — NumberNode wins over VariableDeclaration
        String expected = "{\"contents\":\"100\"}";
        String requestParams = "{\"textDocument\":{\"uri\":\"" + URI + "\"},\"position\":{\"line\":13,\"character\":12}}";
        HoverParams params = objectMapper.readValue(requestParams, HoverParams.class);

        HoverResult result = (HoverResult) hoverHandler.handleMessage(params);
        Assertions.assertEquals(expected, objectMapper.writeValueAsString(result));
    }

    @Test
    public void testHoverOverTopLevelCall() throws Exception {
        // "shrinkingSquares" on line 14, col 5 — before the :size arg, so CallNode is selected
        String expected = "{\"contents\":\"\\nCall(shrinkingSquares, [Var(size)])\"}";
        String requestParams = "{\"textDocument\":{\"uri\":\"" + URI + "\"},\"position\":{\"line\":14,\"character\":5}}";
        HoverParams params = objectMapper.readValue(requestParams, HoverParams.class);

        HoverResult result = (HoverResult) hoverHandler.handleMessage(params);
        Assertions.assertEquals(expected, objectMapper.writeValueAsString(result));
    }
    @Test
    public void testHoverOverProcedureDeclaration() throws Exception {
        // "shrinkingSquares" on line 14, col 5 — before the :size arg, so CallNode is selected
        String expected = "{\"contents\":\"\\nProcedure(shrinkingSquares, [Param(size)], Block[\\nCall(drawSquare, [Var(size)]), \\nIf(Var(size), Block[\\nCall(shrinkingSquares, [\\nCall(difference, [Var(size), 10])])])])\"}";
        String requestParams = "{\"textDocument\":{\"uri\":\"" + URI + "\"},\"position\":{\"line\":6,\"character\":8}}";
        HoverParams params = objectMapper.readValue(requestParams, HoverParams.class);

        HoverResult result = (HoverResult) hoverHandler.handleMessage(params);
        Assertions.assertEquals(expected, objectMapper.writeValueAsString(result));
    }
}
