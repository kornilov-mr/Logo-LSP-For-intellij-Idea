
package org.example.project.ast;
import org.example.communication.DTO.Range;

import java.util.List;

public class CallNode extends ASTNode {

    public final String name;
    public final List<ASTNode> args;

    public CallNode(String name, List<ASTNode> args, Range span) {
        super(span);
        this.name = name;
        this.args = args;
    }

    @Override
    public String toString() {
        return "\nCall(" + name + ", " + args + ")";
    }

    @Override
    public List<ASTNode> getChildren() {
        return args;
    }
}