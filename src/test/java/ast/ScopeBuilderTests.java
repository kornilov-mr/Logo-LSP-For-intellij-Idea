package ast;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class ScopeBuilderTests extends ParserAbstractTest{

    private final File logoScopeBuilder1 = new File("src/test/resources/ast/programs/forScopesTesting/logoFunctionDeclaration.logo");

    public ScopeBuilderTests() {
        super(false);
    }

    @Test
    public void testScopeBuilder1() throws IOException {
        assertMatchesExpectedScopes(logoScopeBuilder1);
    }
}
