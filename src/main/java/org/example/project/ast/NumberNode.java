package org.example.project.ast;

import org.example.communication.DTO.Range;

public class NumberNode extends ASTNode {
    private final String value;

    public NumberNode(String value, Range span) {
        super(span);
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
