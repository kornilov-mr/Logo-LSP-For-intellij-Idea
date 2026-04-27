package org.example.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JsonRPCScanner {

    private final InputStream inputStream;

    public JsonRPCScanner(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String readNextRPCJson() throws IOException {
        int contentLength = -1;

        while (true) {
            StringBuilder lineBuilder = new StringBuilder();
            int b;
            boolean sawCR = false;

            while ((b = inputStream.read()) != -1) {
                if (b == '\r') {
                    sawCR = true;
                    continue;
                }
                if (sawCR && b == '\n') {
                    break;
                }
                if (sawCR) {
                    lineBuilder.append('\r');
                    sawCR = false;
                }
                lineBuilder.append((char) b);
            }

            if (b == -1) {
                throw new EOFException("Stream closed while reading JSON-RPC headers");
            }

            String line = lineBuilder.toString();
            if (line.isEmpty()) {
                break;
            }

            if (line.regionMatches(true, 0, "Content-Length:", 0, "Content-Length:".length())) {
                String value = line.substring("Content-Length:".length()).trim();
                contentLength = Integer.parseInt(value);
            }
        }

        if (contentLength < 0) {
            throw new IOException("Missing Content-Length header");
        }

        byte[] bodyBytes = new byte[contentLength];
        int offset = 0;
        while (offset < contentLength) {
            int read = inputStream.read(bodyBytes, offset, contentLength - offset); // blocking read
            if (read == -1) {
                throw new EOFException("Stream closed before full JSON-RPC body was received");
            }
            offset += read;
        }

        String body = new String(bodyBytes, StandardCharsets.UTF_8);
        return "Content-Length:" + contentLength + "\r\n\r\n" + body;
    }
}
