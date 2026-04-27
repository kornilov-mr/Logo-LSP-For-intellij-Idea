package org.example.project.ast;

import org.example.communication.DTO.Range;

import java.util.List;

public class IfNode extends ASTNode{
    private final ASTNode condition;
    private final BlockNode thenBranch;
    public IfNode(Range range, ASTNode condition, BlockNode thenBranch) {
        super(range);
        this.condition = condition;
        this.thenBranch = thenBranch;
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of(condition, thenBranch);
    }

    @Override
    public String toString() {
        return "\nIf(" + condition + ", " + thenBranch + ")";
    }
}
