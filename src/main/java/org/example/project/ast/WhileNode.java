package org.example.project.ast;

import org.example.communication.DTO.Range;

import java.util.List;

public class WhileNode extends ASTNode{
    private final ASTNode condition;
    private final BlockNode body;
    public WhileNode(Range range, ASTNode condition, BlockNode body) {
        super(range);
        this.condition = condition;
        this.body = body;
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of(condition, body);
    }

    @Override
    public String toString() {
        return "\nWhile(" + condition + ", " + body + ")";
    }
}
