package org.example.project.ast;

import org.example.communication.DTO.Range;
import org.example.project.staticAnalyser.LocalScope;

import java.util.ArrayList;
import java.util.List;

public abstract class ASTNode {

    public final Range range;
    public LocalScope scope;

    protected ASTNode(Range range) {
        this.range = range;
    }

    public Range getSpan(){
        return range;
    }
    public List<ASTNode> getChildren() {
        return List.of();
    }
    public List<ASTNode> getDescendants() {
        List<ASTNode> descendants = new ArrayList<>();
        for(ASTNode astNode : getChildren()){
            descendants.add(astNode);
            descendants.addAll(astNode.getDescendants());
        }
        return descendants;
    }

}
