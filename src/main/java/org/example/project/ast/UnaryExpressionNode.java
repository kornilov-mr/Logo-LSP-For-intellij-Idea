package org.example.project.ast;

import org.example.communication.DTO.Range;

import java.util.List;

public class UnaryExpressionNode extends ASTNode{
    public final String operator;
    private final ASTNode operand;
    public UnaryExpressionNode(Range range, String operator, ASTNode operand) {
        super(range);
        this.operator = operator;
        this.operand = operand;
    }

    @Override
    public String toString() {
        return "UnaryExpr(" + operator + ", " + operand + ")";
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of(operand);
    }
}
