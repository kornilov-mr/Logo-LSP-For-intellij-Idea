package org.example.project;

import org.example.communication.DTO.TextDocumentContentChangeEvent;
import org.example.project.ast.ProgramNode;

import java.util.List;

public class FileNode {
    public final String textContent;
    public final ProgramNode programNode;
    public final FunctionDeclarationTable functionDeclarations;

    public FileNode(String textContent, ProgramNode programNode, FunctionDeclarationTable functionDeclarations) {
        this.textContent = textContent;
        this.programNode = programNode;
        this.functionDeclarations = functionDeclarations;
    }

    public String applyChanges(List<TextDocumentContentChangeEvent> changes) {
        String newText = textContent;
        for (TextDocumentContentChangeEvent change : changes) {
            if (change.range == null) {
                // Full document update
                return change.text;
            } else {
                // Incremental update
                int startLine = change.range.start.line;
                int startChar = change.range.start.character;
                int endLine = change.range.end.line;
                int endChar = change.range.end.character;

                String[] contentLines = newText.split("\n", -1);

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
                newText = newContent.toString();
            }
        }
        return newText;
    }
}
