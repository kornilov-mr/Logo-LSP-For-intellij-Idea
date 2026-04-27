package org.example.project.ast;

import org.example.communication.DTO.Range;

public class NumberLiteral extends ASTNode{
    private final String value;
    protected NumberLiteral(Range range, String value) {
        super(range);
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
