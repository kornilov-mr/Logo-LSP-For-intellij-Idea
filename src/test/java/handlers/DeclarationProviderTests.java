package handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.communication.DTO.Location;
import org.example.communication.requests.DeclarationParams;
import org.example.server.handlers.TextDocumentDeclarationHandler;
import org.example.project.ProjectContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DeclarationProviderTests {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final File variableCompletionFile= new File("src/test/resources/ast/programs/forCompletion/variableCompletion.logo");
    private static final TextDocumentDeclarationHandler declarationHandler  = new TextDocumentDeclarationHandler();
    @BeforeAll
    public static void setup() throws IOException {
        ProjectContext.didOpenFile("src/test/resources/ast/programs/forDeclaration/variableCompletion.logo", Files.readString(variableCompletionFile.toPath()));
    }
    @Test
    public void testVariableCompletionProviderTest1() throws Exception {
        String expected = "{\"uri\":\"src/test/resources/ast/programs/forDeclaration/variableCompletion.logo\",\"range\":{\"start\":{\"line\":0,\"character\":0},\"end\":{\"line\":0,\"character\":9}}}";
        String requestParams ="{\"textDocument\":{\"uri\":\"src/test/resources/ast/programs/forDeclaration/variableCompletion.logo\"},\"position\":{\"line\":3,\"character\":8}}";
        DeclarationParams declarationParams = objectMapper.readValue(requestParams, DeclarationParams.class);

        Location location = (Location) declarationHandler.handleMessage(declarationParams);
        String s = objectMapper.writeValueAsString(location);
        Assertions.assertEquals(expected, objectMapper.writeValueAsString(location));
    }
    @Test
    public void testVariableCompletionProviderTest2() throws Exception {
        String expected = "{\"uri\":\"src/test/resources/ast/programs/forDeclaration/variableCompletion.logo\",\"range\":{\"start\":{\"line\":1,\"character\":0},\"end\":{\"line\":1,\"character\":9}}}";
        String requestParams ="{\"textDocument\":{\"uri\":\"src/test/resources/ast/programs/forDeclaration/variableCompletion.logo\"},\"position\":{\"line\":3,\"character\":11}}";
        DeclarationParams declarationParams = objectMapper.readValue(requestParams, DeclarationParams.class);

        Location location = (Location) declarationHandler.handleMessage(declarationParams);
        Assertions.assertEquals(expected, objectMapper.writeValueAsString(location));
    }
}
