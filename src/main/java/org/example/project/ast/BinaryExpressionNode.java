package org.example.project.ast;

import org.example.communication.DTO.Range;

import java.util.List;

public class BinaryExpressionNode extends ASTNode{
    private final ASTNode left;
    public final String operator;
    private final ASTNode right;
    public BinaryExpressionNode(Range range, ASTNode left, String operator, ASTNode right) {
        super(range);
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public String toString() {
        return "BinaryExpr(" + left + ", " + operator + ", " + right + ")";
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of(left, right);
    }
}
