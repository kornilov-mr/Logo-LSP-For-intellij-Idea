package org.example.project.ast;

import org.example.communication.DTO.Range;

import java.util.List;

public class BlockNode extends ASTNode {
    private final List<ASTNode> body;

    public BlockNode(List<ASTNode> body, Range span) {
        super(span);
        this.body = body;
    }

    @Override
    public String toString() {
        return "Block" + body;
    }

    @Override
    public List<ASTNode> getChildren() {
        return body;
    }
}
