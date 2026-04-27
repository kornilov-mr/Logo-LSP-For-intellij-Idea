package org.example.project.ast;

import org.example.communication.DTO.Range;

import java.util.List;

public class IfElseNode extends ASTNode {
    private final ASTNode condition;
    private final BlockNode thenBranch;
    private final BlockNode elseBranch;
    public IfElseNode(Range range, ASTNode condition, BlockNode thenBranch, BlockNode elseBranch) {
        super(range);
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of(condition, thenBranch,elseBranch);
    }

    @Override
    public String toString() {
        return "\nIfElse(" + condition + ", " + thenBranch + ", " + elseBranch + ")";
    }
}
