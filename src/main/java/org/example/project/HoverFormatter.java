package org.example.project;

import org.example.project.ast.*;

public class HoverFormatter {

    public static String format(ASTNode node, FileNode fileNode) {
        if (node instanceof VariableRefNode varRef) {
            return "variable: " + varRef.name;
        }
        if (node instanceof ParameterNode param) {
            return "parameter: " + param.name;
        }
        if (node instanceof NumberNode num) {
            return num.toString();
        }
        if (node instanceof WordLiteral word) {
            return "\"" + word.value + "\"";
        }
        if (node instanceof VariableDeclaration varDecl) {
            return "variable " + varDecl.variableName + " = " + varDecl.value;
        }
        if (node instanceof CallNode callNode) {
            return describeFunction(callNode.name, fileNode);
        }
        if (node instanceof ProcedureDeclarationNode procDecl) {
            return describeFunction(procDecl.name, fileNode);
        }
        if (node instanceof FunctionRef funcRef) {
            return describeFunction(funcRef.name, fileNode);
        }
        if (node instanceof RepeatNode) {
            return describeBuiltin("repeat");
        }
        if (node instanceof IfElseNode) {
            return describeBuiltin("ifelse");
        }
        if (node instanceof IfNode) {
            return describeBuiltin("if");
        }
        if (node instanceof WhileNode) {
            return describeBuiltin("while");
        }
        if (node instanceof BinaryExpressionNode binExpr) {
            return "operator: " + binExpr.operator;
        }
        if (node instanceof UnaryExpressionNode unaryExpr) {
            return "operator: " + unaryExpr.operator;
        }
        return node.toString();
    }

    private static String describeFunction(String name, FileNode fileNode) {
        FunctionDeclaration decl = fileNode.functionDeclarations.getFunctionDeclaration(name);
        if (decl != null && decl.description != null) return decl.description;
        FunctionDeclaration builtin = StandardFunctionDeclaration.getDeclaration().getFunctionDeclaration(name);
        if (builtin != null && builtin.description != null) return builtin.description;
        return "function: " + name;
    }

    private static String describeBuiltin(String name) {
        FunctionDeclaration decl = StandardFunctionDeclaration.getDeclaration().getFunctionDeclaration(name);
        if (decl != null && decl.description != null) return decl.description;
        return name;
    }
}
