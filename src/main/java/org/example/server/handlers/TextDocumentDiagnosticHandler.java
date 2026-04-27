package org.example.server.handlers;

import org.example.communication.DTO.Diagnostic;
import org.example.communication.requests.DocumentDiagnosticParams;
import org.example.communication.responses.DocumentDiagnosticReport;
import org.example.project.FileNode;
import org.example.project.ProjectContext;
import org.example.project.ast.ErrorNode;
import org.example.project.ast.ProgramNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles diagnostics for a text document in the context of the Language Server Protocol (LSP).
 * <p>
 * This class extends {@code LSPHandler} to process {@code DocumentDiagnosticParams} requests
 * and return {@code DocumentDiagnosticReport} responses. It analyzes the state of a text document
 * by leveraging the corresponding {@code FileNode} and its associated {@code ProgramNode}.
 * The diagnostics include parsing errors and static analysis issues detected in the document.
 */
public class TextDocumentDiagnosticHandler extends LSPHandler<DocumentDiagnosticParams, DocumentDiagnosticReport> {

    private static final int SEVERITY_ERROR = 1;

    @Override
    protected DocumentDiagnosticReport handle(DocumentDiagnosticParams params) {
        FileNode fileNode = ProjectContext.getFileNode(params.textDocument.uri);
        ProgramNode programNode = fileNode.programNode;

        List<Diagnostic> diagnostics = new ArrayList<>();

        for (ErrorNode error : programNode.parserErrors) {
            diagnostics.add(new Diagnostic(error.getSpan(), SEVERITY_ERROR, "parser", error.getMessage()));
        }

        for (ErrorNode error : programNode.staticErrors) {
            diagnostics.add(new Diagnostic(error.getSpan(), SEVERITY_ERROR, "static", error.getMessage()));
        }

        return new DocumentDiagnosticReport(diagnostics);
    }
}
