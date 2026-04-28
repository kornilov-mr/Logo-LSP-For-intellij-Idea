import org.example.communication.DTO.Position;
import org.example.communication.DTO.Range;
import org.example.communication.DTO.TextDocumentContentChangeEvent;
import org.example.project.FileNode;
import org.example.project.ast.ConvertToAST;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FileIncrementalSyncTests {

    @Test
    public void testFileIncrementalSyncSingleRangeOneLine() {
        String expected = "Test New content check if one range works";

        FileNode fileNode = ConvertToAST.convert("Test Content check if one range works");
        List<TextDocumentContentChangeEvent> changes = new ArrayList<>();
        changes.add(new TextDocumentContentChangeEvent(
                new Range(new Position(0, 5), new Position(0, 12)),
                "New content"));
        String newText = fileNode.applyChanges(changes);

        Assertions.assertEquals(expected, newText);
        Assertions.assertEquals(1, newText.split("\n", -1).length);
    }

    @Test
    public void testMultipleChanges() {
        String initial = "Line one\nLine two\nLine three";
        FileNode fileNode = ConvertToAST.convert(initial);

        List<TextDocumentContentChangeEvent> changes = new ArrayList<>();
        // First change: replace "one" with "1"
        changes.add(new TextDocumentContentChangeEvent(
                new Range(new Position(0, 5), new Position(0, 8)),
                "1"));
        // Second change: replace "two" with "2"
        changes.add(new TextDocumentContentChangeEvent(
                new Range(new Position(1, 5), new Position(1, 8)),
                "2"));

        String newText = fileNode.applyChanges(changes);

        String expected = "Line 1\nLine 2\nLine three";
        Assertions.assertEquals(expected, newText);
        Assertions.assertEquals(3, newText.split("\n", -1).length);
    }

    @Test
    public void testMultipleContentLines() {
        String initial = "First line\nSecond line\nThird line\nFourth line";
        FileNode fileNode = ConvertToAST.convert(initial);

        List<TextDocumentContentChangeEvent> changes = new ArrayList<>();
        // Replace "Second line\nThird" with "MODIFIED"
        changes.add(new TextDocumentContentChangeEvent(
                new Range(new Position(1, 0), new Position(2, 5)),
                "MODIFIED"));

        String newText = fileNode.applyChanges(changes);

        String expected = "First line\nMODIFIED line\nFourth line";
        Assertions.assertEquals(expected, newText);
        Assertions.assertEquals(3, newText.split("\n", -1).length);
    }

    @Test
    public void testChangesWhichAddLines() {
        String initial = "Line one\nLine two";
        FileNode fileNode = ConvertToAST.convert(initial);

        List<TextDocumentContentChangeEvent> changes = new ArrayList<>();
        // Insert new lines between "one" and "Line two"
        changes.add(new TextDocumentContentChangeEvent(
                new Range(new Position(0, 8), new Position(0, 8)),
                "\nNew line 1\nNew line 2"));

        String newText = fileNode.applyChanges(changes);

        String expected = "Line one\nNew line 1\nNew line 2\nLine two";
        Assertions.assertEquals(expected, newText);
        Assertions.assertEquals(4, newText.split("\n", -1).length);
    }

    @Test
    public void testChangesAppliedSequentially() {
        String initial = "ABCDEF";
        FileNode fileNode = ConvertToAST.convert(initial);

        List<TextDocumentContentChangeEvent> changes = new ArrayList<>();
        // First change: insert "1" after "ABC"
        changes.add(new TextDocumentContentChangeEvent(
                new Range(new Position(0, 3), new Position(0, 3)),
                "1"));
        // Second change: should be applied to result of first change
        // Insert "2" after "ABC1D" (position 4)
        changes.add(new TextDocumentContentChangeEvent(
                new Range(new Position(0, 4), new Position(0, 4)),
                "2"));

        String newText = fileNode.applyChanges(changes);

        String expected = "ABC12DEF";
        Assertions.assertEquals(expected, newText);
        Assertions.assertEquals(1, newText.split("\n", -1).length);
    }

    @Test
    public void testAddLinesAtBeginning() {
        String initial = "Original content";
        FileNode fileNode = ConvertToAST.convert(initial);

        List<TextDocumentContentChangeEvent> changes = new ArrayList<>();
        changes.add(new TextDocumentContentChangeEvent(
                new Range(new Position(0, 0), new Position(0, 0)),
                "New first line\n"));

        String newText = fileNode.applyChanges(changes);

        String expected = "New first line\nOriginal content";
        Assertions.assertEquals(expected, newText);
        Assertions.assertEquals(2, newText.split("\n", -1).length);
    }

    @Test
    public void testAddLinesAtEnd() {
        String initial = "Original content";
        FileNode fileNode = ConvertToAST.convert(initial);

        List<TextDocumentContentChangeEvent> changes = new ArrayList<>();
        changes.add(new TextDocumentContentChangeEvent(
                new Range(new Position(0, 16), new Position(0, 16)),
                "\nNew last line"));

        String newText = fileNode.applyChanges(changes);

        String expected = "Original content\nNew last line";
        Assertions.assertEquals(expected, newText);
        Assertions.assertEquals(2, newText.split("\n", -1).length);
    }
}