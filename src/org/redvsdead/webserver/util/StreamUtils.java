package org.redvsdead.webserver.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtils {
    public static String BufferedReaderToString(BufferedReader reader) throws IOException {
        var sb = new StringBuffer();
        int i;
        while ((i = reader.read()) != -1) {
            if (!reader.ready()) {
                break;
            }
            char c = (char) i;
            sb.append(c);
        }
        return sb.toString();
    }

    public static String InputStreamToString(InputStream stream) throws IOException {
        var sb = new StringBuffer();
        try (var dataInputStream = new DataInputStream(stream)) {
            int i;
            while ((i = dataInputStream.read()) != -1)
            {
                char c = (char) i;
                sb.append(c);
            }
        return sb.toString();
        }
    }
}