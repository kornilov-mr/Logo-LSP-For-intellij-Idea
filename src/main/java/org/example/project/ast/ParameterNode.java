package org.example.project.ast;

import org.example.communication.DTO.Range;

public class ParameterNode extends ASTNode {
    public final String name;

    public ParameterNode(Range range, String name) {
        super(range);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Param(" + name + ")";
    }
}
