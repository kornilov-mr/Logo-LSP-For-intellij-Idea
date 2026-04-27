package org.example.project.staticAnalyser;

import org.example.project.UserVariableDeclaration;
import org.example.project.ast.*;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class ScopeBuilder {

    private final Deque<LocalScope> scopesStack = new ArrayDeque<>();

    public void buildScope(ProgramNode programNode) {

        walkAndBuildScope(programNode, new LocalScope());
    }

    private void walkAndBuildScope(ASTNode astNode, LocalScope currentScope) {
        for (ASTNode child : astNode.getChildren()) {
            child.scope = currentScope;
            if (child instanceof BlockNode) {
                scopesStack.push(currentScope);
                LocalScope newScope = currentScope.copyScope();
                walkAndBuildScope(child, newScope);
                currentScope = scopesStack.pop();
                continue;
            }
            if (child instanceof VariableDeclaration variableDeclaration) {
                LocalScope newScope = currentScope.copyScope();
                UserVariableDeclaration userVariableDeclaration = new UserVariableDeclaration(variableDeclaration.variableName, variableDeclaration.getSpan());
                newScope.declaredVariables.put(variableDeclaration.variableName, userVariableDeclaration);
                walkAndBuildScope(child, newScope);
                currentScope = newScope;
                continue;
            }
            if (child instanceof ProcedureDeclarationNode procedureDeclarationNode) {
                // Outer scope gains only the procedure name — visible to subsequent statements.
                LocalScope newScope = currentScope.copyScope();
                newScope.localFunctionDeclarations.add(procedureDeclarationNode.getName(),
                        procedureDeclarationNode.transformToDeclaration());
                // Inner scope additionally carries the parameters — discarded after the body.
                LocalScope procedureScope = newScope.copyScope();
                for (ParameterNode parameterNode : procedureDeclarationNode.getParameters()) {
                    UserVariableDeclaration userVariableDeclaration = new UserVariableDeclaration(parameterNode.name, parameterNode.getSpan());
                    procedureScope.declaredVariables.put(parameterNode.name, userVariableDeclaration);
                }
                walkAndBuildScope(child, procedureScope);
                currentScope = newScope;
                continue;
            }
            walkAndBuildScope(child, currentScope);
        }
    }

    public static String PrintAllScopesInProgram(ProgramNode programNode) {
        List<ASTNode> allNodes = programNode.getDescendants();
        LocalScope scope = new LocalScope();
        StringBuilder scopeString = new StringBuilder();
        for (ASTNode node : allNodes) {
            if (scope != node.scope) {
                scopeString.append(node.scope.toString() + "\n");
                scope = node.scope;
            }
        }
        return scopeString.toString();
    }
}
