package ast;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class ConvertWithStaticErrorsTest extends ParserAbstractTest {

    private final File undefinedFunction = new File("src/test/resources/ast/programs/withStaticErrors/undefinedFunction.logo");
    private final File undefinedVariable = new File("src/test/resources/ast/programs/withStaticErrors/undefinedVariable.logo");

    public ConvertWithStaticErrorsTest() {
        super(false);
    }

    @Test
    public void testUndefinedFunctionError() throws IOException {
        super.assertMatchesExpectedParsing(undefinedFunction);
    }
    @Test
    public void testUndefinedVariableErrors() throws IOException {
        super.assertMatchesExpectedParsing(undefinedVariable);
    }
}
