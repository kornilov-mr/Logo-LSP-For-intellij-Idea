package ast;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class ConvertToASTWithArityResolveTest extends ParserAbstractTest {

    private final File logoArity1 = new File("src/test/resources/ast/programs/validLogo/logoArityResolve1.logo");
    private final File logoFunctionDeclarationWithArity = new File("src/test/resources/ast/programs/validLogo/logoFunctionDeclaration.logo");

    public ConvertToASTWithArityResolveTest() {
        super(false);
    }

    @Test
    public void convertSimpleLogoWithNoArityResolve() throws IOException {
        super.assertMatchesExpectedParsing(logoArity1);
    }
    @Test
    public void convertLogoFunctionDeclarationWithArity() throws IOException {
        super.assertMatchesExpectedParsing(logoFunctionDeclarationWithArity);
    }
}
