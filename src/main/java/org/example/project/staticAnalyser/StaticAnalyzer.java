package org.example.project.staticAnalyser;

import org.example.project.ast.*;

import java.util.ArrayList;
import java.util.List;

public class StaticAnalyzer {

    private final ScopeBuilder scopeBuilder = new ScopeBuilder();
    private final List<ErrorNode> errors = new ArrayList<>();

    public List<ErrorNode> processProgramNode(ProgramNode programNode){
        scopeBuilder.buildScope(programNode);
        walk(programNode);
        return errors;
    }
    public void walk(ASTNode astNode){
        for (ASTNode child : astNode.getChildren()) {
            if (child instanceof CallNode callNode) {
                if(!callNode.scope.containsFunction(callNode.name)) {
                    errors.add(new ErrorNode("Undefined function: " + callNode.name, callNode.getSpan()));
                }
            }
            if (child instanceof FunctionRef functionRef) {
                if(!functionRef.scope.containsFunction(functionRef.name)) {
                    errors.add(new ErrorNode("Undefined function: " + functionRef.name, functionRef.getSpan()));
                }
            }
            if (child instanceof VariableRefNode variableRefNode) {
                if(!variableRefNode.scope.containsVariable(variableRefNode.name)) {
                    errors.add(new ErrorNode("Undefined variable: " + variableRefNode.name, variableRefNode.getSpan()));
                }
            }
            walk(child);
        }
    }
}
