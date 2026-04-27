package org.example.project.parser;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.example.communication.DTO.Position;
import org.example.communication.DTO.Range;
import org.example.project.ast.ErrorNode;

import java.util.ArrayList;
import java.util.List;

public class LogoSyntaxErrorCollector extends BaseErrorListener {

    private final List<ErrorNode> errors = new ArrayList<>();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e) {
        Token token = offendingSymbol instanceof Token t ? t : null;
        int length = token != null && token.getText() != null ? token.getText().length() : 0;
        Position start = new Position(line - 1, charPositionInLine);
        Position end   = new Position(line - 1, charPositionInLine + length);
        errors.add(new ErrorNode(msg, new Range(start, end)));
    }

    public List<ErrorNode> getErrors() {
        return List.copyOf(errors);
    }
}
