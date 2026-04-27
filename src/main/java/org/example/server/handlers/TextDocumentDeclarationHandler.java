package org.example.server.handlers;

import org.example.communication.DTO.Location;
import org.example.communication.LSPAny;
import org.example.communication.requests.DeclarationParams;
import org.example.project.FunctionDeclaration;
import org.example.project.FileNode;
import org.example.project.ProjectContext;
import org.example.project.UserVariableDeclaration;
import org.example.project.ast.ASTNode;
import org.example.project.ast.CallNode;
import org.example.project.ast.ProgramNode;
import org.example.project.ast.VariableRefNode;

/**
 * Handles "textDocument/declaration" requests in a Language Server Protocol (LSP) context.
 * <p>
 * This class processes requests to resolve declarations for symbols in a source file
 * and provides their locations. It extends the generic {@code LSPHandler} by implementing
 * the logic specific to a "declaration" request, which involves locating variable or
 * function declarations based on identifier usage in the code.
 * <p>
 * The following types of symbols are handled:
 * 1. Function calls: Resolves to the declaration of the called function.
 * 2. Variable references: Resolves to the declaration of the referred variable.
 * <p>
 * If a declaration cannot be found, appropriate exceptions or fallback results are returned.
 */
public class TextDocumentDeclarationHandler extends LSPHandler<DeclarationParams, LSPAny> {

    @Override
    protected LSPAny handle(DeclarationParams params) {
        FileNode fileNode = ProjectContext.getFileNode(params.textDocument.uri);
        ProgramNode programNode = fileNode.programNode;
        ASTNode node = programNode.getNodesInSpan(params.position);

        if (node instanceof CallNode callNode) {
            FunctionDeclaration decl = node.scope.localFunctionDeclarations.getFunctionDeclaration(callNode.name);
            if (decl == null || decl.range == null) {
                return null;
            }
            return new Location(params.textDocument.uri, decl.range);
        }

        if (node instanceof VariableRefNode varRef) {
            if(!node.scope.declaredVariables.containsKey(varRef.name))
                return null;
            UserVariableDeclaration declaration = node.scope.declaredVariables.get(varRef.name);
            return new Location(params.textDocument.uri, declaration.range);
        }

        return null;
    }
}
