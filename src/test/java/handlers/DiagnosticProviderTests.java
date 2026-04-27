package handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.communication.DTO.Diagnostic;
import org.example.communication.requests.DocumentDiagnosticParams;
import org.example.communication.responses.DocumentDiagnosticReport;
import org.example.server.handlers.TextDocumentDiagnosticHandler;
import org.example.project.ProjectContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class DiagnosticProviderTests {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final TextDocumentDiagnosticHandler diagnosticHandler = new TextDocumentDiagnosticHandler();

    private static final String VALID_URI = "src/test/resources/ast/programs/validLogo/logoMake.logo";
    private static final String SYNTAX_ERROR_URI = "src/test/resources/ast/programs/withSyntaxError/logoMakeError.logo";
    private static final String UNDEFINED_VAR_URI = "src/test/resources/ast/programs/withStaticErrors/undefinedVariable.logo";
    private static final String UNDEFINED_FN_URI = "src/test/resources/ast/programs/withStaticErrors/undefinedFunction.logo";

    @BeforeAll
    public static void setup() throws IOException {
        ProjectContext.didOpenFile(VALID_URI, Files.readString(new File(VALID_URI).toPath()));
        ProjectContext.didOpenFile(SYNTAX_ERROR_URI, Files.readString(new File(SYNTAX_ERROR_URI).toPath()));
        ProjectContext.didOpenFile(UNDEFINED_VAR_URI, Files.readString(new File(UNDEFINED_VAR_URI).toPath()));
        ProjectContext.didOpenFile(UNDEFINED_FN_URI, Files.readString(new File(UNDEFINED_FN_URI).toPath()));
    }

    @Test
    public void testNoDiagnosticsForValidProgram() throws Exception {
        DocumentDiagnosticParams params = paramsFor(VALID_URI);

        DocumentDiagnosticReport report = (DocumentDiagnosticReport) diagnosticHandler.handleMessage(params);

        Assertions.assertEquals("full", report.kind);
        Assertions.assertTrue(report.items.isEmpty());
    }

    @Test
    public void testSyntaxErrorsAreReportedAsParserDiagnostics() throws Exception {
        DocumentDiagnosticParams params = paramsFor(SYNTAX_ERROR_URI);

        DocumentDiagnosticReport report = (DocumentDiagnosticReport) diagnosticHandler.handleMessage(params);

        Assertions.assertEquals("full", report.kind);
        Assertions.assertFalse(report.items.isEmpty());

        List<Diagnostic> parserErrors = report.items.stream()
                .filter(d -> "parser".equals(d.source))
                .toList();
        Assertions.assertFalse(parserErrors.isEmpty());

        for (Diagnostic d : parserErrors) {
            Assertions.assertEquals(1, d.severity);
            Assertions.assertNotNull(d.range);
            Assertions.assertNotNull(d.range.start);
            Assertions.assertNotNull(d.range.end);
            Assertions.assertNotNull(d.message);
            Assertions.assertFalse(d.message.isBlank());
        }
    }

    @Test
    public void testUndefinedVariableReportedAsStaticDiagnostic() throws Exception {
        DocumentDiagnosticParams params = paramsFor(UNDEFINED_VAR_URI);

        DocumentDiagnosticReport report = (DocumentDiagnosticReport) diagnosticHandler.handleMessage(params);

        Assertions.assertEquals("full", report.kind);

        List<Diagnostic> staticErrors = report.items.stream()
                .filter(d -> "static".equals(d.source))
                .toList();
        Assertions.assertFalse(staticErrors.isEmpty());

        Diagnostic error = staticErrors.get(0);
        Assertions.assertEquals(1, error.severity);
        Assertions.assertNotNull(error.range);
        Assertions.assertEquals(2, error.range.start.line);
    }

    @Test
    public void testUndefinedFunctionReportedAsStaticDiagnostic() throws Exception {
        DocumentDiagnosticParams params = paramsFor(UNDEFINED_FN_URI);

        DocumentDiagnosticReport report = (DocumentDiagnosticReport) diagnosticHandler.handleMessage(params);

        Assertions.assertEquals("full", report.kind);

        List<Diagnostic> staticErrors = report.items.stream()
                .filter(d -> "static".equals(d.source))
                .toList();
        Assertions.assertFalse(staticErrors.isEmpty());

        for (Diagnostic d : staticErrors) {
            Assertions.assertEquals(1, d.severity);
            Assertions.assertNotNull(d.range);
            Assertions.assertFalse(d.message.isBlank());
        }
    }

    @Test
    public void testDiagnosticReportHasNoParserErrorsForStaticOnlyFile() throws Exception {
        DocumentDiagnosticParams params = paramsFor(UNDEFINED_VAR_URI);

        DocumentDiagnosticReport report = (DocumentDiagnosticReport) diagnosticHandler.handleMessage(params);

        List<Diagnostic> parserErrors = report.items.stream()
                .filter(d -> "parser".equals(d.source))
                .toList();
        Assertions.assertTrue(parserErrors.isEmpty());
    }

    @Test
    public void testDiagnosticResponseIsSerializableToJson() throws Exception {
        DocumentDiagnosticParams params = paramsFor(SYNTAX_ERROR_URI);

        DocumentDiagnosticReport report = (DocumentDiagnosticReport) diagnosticHandler.handleMessage(params);
        String json = objectMapper.writeValueAsString(report);

        Assertions.assertTrue(json.contains("\"kind\":\"full\""));
        Assertions.assertTrue(json.contains("\"items\""));
        Assertions.assertTrue(json.contains("\"severity\":1"));
        Assertions.assertTrue(json.contains("\"source\":\"parser\""));
    }

    private static DocumentDiagnosticParams paramsFor(String uri) throws Exception {
        String json = "{\"textDocument\":{\"uri\":\"" + uri + "\"}}";
        return objectMapper.readValue(json, DocumentDiagnosticParams.class);
    }
}
