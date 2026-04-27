package org.example.project.ast;

import org.example.communication.DTO.Range;

public class FunctionRef  extends ASTNode {
    public final String name;

    public FunctionRef(String name, Range span) {
        super(span);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Var(" + name + ")";
    }
}
