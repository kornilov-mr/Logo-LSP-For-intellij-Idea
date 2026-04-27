import org.example.communication.DTO.Position;
import org.example.communication.DTO.TextDocument;
import org.example.communication.requests.LSPRequestWrapper;
import org.example.communication.requests.HoverParams;
import org.example.communication.responses.LSPResponseError;
import org.example.communication.EncodingMessage;
import org.example.communication.Message;
import org.example.communication.ResponseErrorsCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MessageEncodingTests {

    @Test
    public void testLengthSimpleResponseMessage(){
        String expected = "Content-Length:32\r\n\r\n{\"code\":-32001,\"message\":\"test\"}";
        LSPResponseError responseError = new LSPResponseError(ResponseErrorsCode.UnknownErrorCode,"test");
        String RPC = EncodingMessage.encodeToJsonRPC(responseError);
        Assertions.assertEquals(expected,RPC);
    }
    @Test
    public void testEncodingAndDecodingSame(){
        LSPRequestWrapper LSPRequest = new LSPRequestWrapper(1,"textDocument/hover",new HoverParams(new TextDocument("testFile"),new Position(1,1)));
        String RPC = EncodingMessage.encodeToJsonRPC(LSPRequest);
        Message afterDecoding = EncodingMessage.decodeFromJsonRPC(RPC);
        Assertions.assertEquals(LSPRequest,afterDecoding,"Message isn't the same after encoding and decoding");
    }
}
