package org.example.project;

import org.example.communication.DTO.TextDocumentContentChangeEvent;
import org.example.project.ast.ConvertToAST;
import org.example.project.ast.ProgramNode;

import java.util.ArrayList;
import java.util.List;

public class FileNode {

    public String textContent;


    public ProgramNode programNode;
    public final FunctionDeclarationTable functionDeclarations = new FunctionDeclarationTable();

    public FileNode(String textContent) {
        this.textContent = textContent;
    }

    public synchronized void applyChanges(List<TextDocumentContentChangeEvent> changes) {
        for (TextDocumentContentChangeEvent change : changes) {
            if (change.range == null) {
                // Full document update
                this.textContent = change.text;
            } else {
                // Incremental update
                int startLine = change.range.start.line;
                int startChar = change.range.start.character;
                int endLine = change.range.end.line;
                int endChar = change.range.end.character;

                String[] contentLines = this.textContent.split("\n", -1);

                // Build the new content
                StringBuilder newContent = new StringBuilder();

                // Keep lines before the change
                for (int i = 0; i < startLine; i++) {
                    newContent.append(contentLines[i]).append("\n");
                }

                // Add the part of the start line before the change
                if (startLine < contentLines.length) {
                    String startLineContent = contentLines[startLine];
                    newContent.append(startLineContent, 0, Math.min(startChar, startLineContent.length()));
                }

                // Add the new text
                newContent.append(change.text);

                // Add the part of the end line after the change
                if (endLine < contentLines.length) {
                    String endLineContent = contentLines[endLine];
                    newContent.append(endLineContent.substring(Math.min(endChar, endLineContent.length())));
                }

                // Add remaining lines after the change
                for (int i = endLine + 1; i < contentLines.length; i++) {
                    newContent.append("\n").append(contentLines[i]);
                }

                // Update textContent
                this.textContent = newContent.toString();
            }
        }
    }
    public void processNode() {
        this.programNode = ConvertToAST.convert(this);
    }
}
