package org.example.project.ast;

import org.example.communication.DTO.Range;

import java.util.List;

public class ListLiteralNode extends ASTNode{
    public final List<ASTNode> elements;
    protected ListLiteralNode(Range range, List<ASTNode> elements) {
        super(range);
        this.elements = elements;
    }

    @Override
    public String toString() {
        return "List(" + elements + ")";
    }
    @Override
    public List<ASTNode> getChildren() {
        return elements;
    }
}
