package org.example.project.ast;

import gen.LogoLexer;
import gen.LogoParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.example.project.FileNode;
import org.example.project.FunctionDeclarationTable;
import org.example.project.parser.LogoArityResolverListener;
import org.example.project.parser.LogoSyntaxErrorCollector;
import org.example.project.staticAnalyser.StaticAnalyzer;


public class ConvertToAST {
    public static FileNode convert(String text) {
        CharStream input = CharStreams.fromString(text);
        LogoLexer lexer = new LogoLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LogoParser parser = new LogoParser(tokens);

        FunctionDeclarationTable functionDeclarations = new FunctionDeclarationTable();

        LogoSyntaxErrorCollector errorCollector = new LogoSyntaxErrorCollector();
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorCollector);
        parser.removeErrorListeners();
        parser.addErrorListener(errorCollector);

        ParseTree tree = parser.program();
        StaticAnalyzer staticAnalyzer = new StaticAnalyzer();

        LogoArityResolverListener listener =
                new LogoArityResolverListener(functionDeclarations);

        ParseTreeWalker.DEFAULT.walk(listener, tree);
        ProgramNode programNode = listener.getResult();
        programNode.parserErrors.addAll(errorCollector.getErrors());

        programNode.staticErrors.addAll(staticAnalyzer.processProgramNode(programNode));

        return new FileNode(text, programNode, functionDeclarations);
    }
}
