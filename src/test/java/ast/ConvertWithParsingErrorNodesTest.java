package ast;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class ConvertWithParsingErrorNodesTest extends ParserAbstractTest{

    private final File logoMakeVariableError = new File("src/test/resources/ast/programs/withSyntaxError/logoMakeError.logo");
    private final File logoWrongFunctionNameError = new File("src/test/resources/ast/programs/withSyntaxError/logoWrongFunctionNameError.logo");

    public ConvertWithParsingErrorNodesTest() {
        super(false);
    }

    @Test
    public void testParseErrorNodes() throws IOException {
        assertMatchesExpectedParsing(logoMakeVariableError);
    }
    @Test
    public void testParseWrongFunctionNameErrorNodes() throws IOException {
        assertMatchesExpectedParsing(logoWrongFunctionNameError);
    }
}
