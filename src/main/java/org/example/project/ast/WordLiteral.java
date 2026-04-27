package org.example.project.ast;

import org.example.communication.DTO.Range;

public class WordLiteral extends ASTNode{
    private final String value;
    public WordLiteral(Range range, String value) {
        super(range);
        this.value = value;
    }

    @Override
    public String toString() {
        return "Word(" + value + ")";
    }
}
