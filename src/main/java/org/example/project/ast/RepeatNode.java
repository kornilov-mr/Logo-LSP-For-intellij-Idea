package org.example.project.ast;

import org.example.communication.DTO.Range;

import java.util.List;

public class RepeatNode extends ASTNode {
    private final ASTNode count;
    private final BlockNode body;

    public RepeatNode(ASTNode count, BlockNode body, Range span) {
        super(span);
        this.count = count;
        this.body = body;
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of(count, body);
    }

    @Override
    public String toString() {
        return "\nRepeat(" + count + ", " + body + ")";
    }
}