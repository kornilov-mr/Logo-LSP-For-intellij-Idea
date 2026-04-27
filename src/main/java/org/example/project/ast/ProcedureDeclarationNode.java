package org.example.project.ast;

import org.example.communication.DTO.Range;
import org.example.project.FunctionDeclaration;

import java.util.List;

public class ProcedureDeclarationNode extends ASTNode {

    public final String name;
    public final Range nameRange;
    public final List<ParameterNode> parameters;
    public final BlockNode body;

    public ProcedureDeclarationNode(String name, List<ParameterNode> parameters, BlockNode body, Range span, Range nameRange) {
        super(span);
        this.name = name;
        this.nameRange = nameRange;
        this.parameters = parameters;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public List<ParameterNode> getParameters() {
        return parameters;
    }

    public BlockNode getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "\nProcedure(" + name + ", " + parameters + ", " + body + ")";
    }

    @Override
    public List<ASTNode> getChildren() {
        List<ASTNode> allNodes = new java.util.ArrayList<>(parameters);
        allNodes.add(body);
        return allNodes;
    }

    public FunctionDeclaration transformToDeclaration() {
        return new FunctionDeclaration(name, parameters.size(), FunctionDeclaration.Kind.USER, getSpan());
    }
}