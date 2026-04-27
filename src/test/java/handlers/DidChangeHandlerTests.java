package handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.communication.DTO.Position;
import org.example.communication.DTO.Range;
import org.example.communication.DTO.TextDocumentContentChangeEvent;
import org.example.communication.DTO.VersionedTextDocumentIdentifier;
import org.example.communication.notification.DidChangeTextDocumentParams;
import org.example.communication.requests.DocumentDiagnosticParams;
import org.example.communication.responses.DocumentDiagnosticReport;
import org.example.project.FileNode;
import org.example.project.ProjectContext;
import org.example.server.handlers.TextDocumentDiagnosticHandler;
import org.example.server.handlers.TextDocumentDidChangeHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DidChangeHandlerTests {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final TextDocumentDidChangeHandler changeHandler = new TextDocumentDidChangeHandler();
    private static final TextDocumentDiagnosticHandler diagnosticHandler = new TextDocumentDiagnosticHandler();
    private static final String URI = "test://did-change-tests.logo";
    private static final String INITIAL_CONTENT = "forward 100\nright 90";

    @BeforeEach
    public void setup() {
        ProjectContext.didOpenFile(URI, INITIAL_CONTENT);
    }

    @Test
    public void testFullDocumentReplacementUpdatesTextContent() throws Exception {
        changeHandler.handleMessage(fullChange("forward 200"));

        Assertions.assertEquals("forward 200", ProjectContext.getFileNode(URI).textContent);
    }

    @Test
    public void testIncrementalEditUpdatesTextContent() throws Exception {
        // Replace "100" (line 0, chars 8–11) with "200"
        changeHandler.handleMessage(rangeChange(0, 8, 0, 11, "200"));

        Assertions.assertEquals("forward 200\nright 90", ProjectContext.getFileNode(URI).textContent);
    }

    @Test
    public void testFullReplacementReparsesAstCleanly() throws Exception {
        changeHandler.handleMessage(fullChange("forward 50\nback 25"));

        FileNode fileNode = ProjectContext.getFileNode(URI);
        Assertions.assertTrue(fileNode.programNode.parserErrors.isEmpty());
        Assertions.assertTrue(fileNode.programNode.staticErrors.isEmpty());
    }

    @Test
    public void testChangeIntroducesUndefinedVariableError() throws Exception {
        // :undeclared is never declared — should produce a static error
        changeHandler.handleMessage(fullChange("print :undeclared"));

        FileNode fileNode = ProjectContext.getFileNode(URI);
        Assertions.assertFalse(fileNode.programNode.staticErrors.isEmpty());
    }

    @Test
    public void testChangeFixesStaticError() throws Exception {
        // Introduce an undefined variable error
        changeHandler.handleMessage(fullChange("print :undeclared"));
        Assertions.assertFalse(ProjectContext.getFileNode(URI).programNode.staticErrors.isEmpty());

        // Fix it by declaring the variable first
        changeHandler.handleMessage(fullChange("make \"undeclared 1\nprint :undeclared"));
        Assertions.assertTrue(ProjectContext.getFileNode(URI).programNode.staticErrors.isEmpty());
    }

    @Test
    public void testIncrementalMultiCharacterEdit() throws Exception {
        // Replace "right" (line 1, chars 0–5) with "left"
        changeHandler.handleMessage(rangeChange(1, 0, 1, 5, "left"));

        Assertions.assertEquals("forward 100\nleft 90", ProjectContext.getFileNode(URI).textContent);
    }

    @Test
    public void testDiagnosticHandlerReflectsChangeAfterEdit() throws Exception {
        // Introduce a parser error by putting an incomplete procedure definition
        changeHandler.handleMessage(fullChange("to broken"));

        DocumentDiagnosticParams diagParams = objectMapper.readValue(
                "{\"textDocument\":{\"uri\":\"" + URI + "\"}}", DocumentDiagnosticParams.class);
        DocumentDiagnosticReport report = (DocumentDiagnosticReport) diagnosticHandler.handleMessage(diagParams);

        Assertions.assertFalse(report.items.isEmpty());
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    private static DidChangeTextDocumentParams fullChange(String newContent) {
        return new DidChangeTextDocumentParams(
                new VersionedTextDocumentIdentifier(URI, 2),
                List.of(new TextDocumentContentChangeEvent(null, newContent)));
    }

    private static DidChangeTextDocumentParams rangeChange(int startLine, int startChar,
                                                            int endLine, int endChar, String text) {
        Range range = new Range(new Position(startLine, startChar), new Position(endLine, endChar));
        return new DidChangeTextDocumentParams(
                new VersionedTextDocumentIdentifier(URI, 2),
                List.of(new TextDocumentContentChangeEvent(range, text)));
    }
}
