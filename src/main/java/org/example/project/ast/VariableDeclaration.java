package org.example.project.ast;

import org.example.communication.DTO.Range;

import java.util.List;

public class VariableDeclaration extends ASTNode {

    public final String variableName;
    public final Range nameRange;
    public final ASTNode value;

    public VariableDeclaration(String variableName, ASTNode value, Range span, Range nameRange) {
        super(span);
        this.variableName = variableName;
        this.nameRange = nameRange;
        this.value = value;
    }

    @Override
    public String toString() {
        return "\nDeclare(" + variableName + ", " + value + ")";
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of(value);
    }
}
