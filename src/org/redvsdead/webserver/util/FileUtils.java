package org.redvsdead.webserver.util;

import java.io.IOException;

public class FileUtils {
    public static byte[] readFile(String filePath) {
        try {
            var bytes = FileUtils.class.getResourceAsStream(filePath).readAllBytes();
            return bytes;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new byte[0];
        }
    }
}
