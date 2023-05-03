package org.redvsdead.webserver.http;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class HttpRequestParser {
    public static String extractRequestMethod(String request) {
        var matcher = Pattern.compile("(?:^|(?:[.!?]\\s))(\\w+)").matcher(request);
        while (matcher.find()) {
            return matcher.group();
        }
        return "";
    }

    public static String extractRequestPath(String request) {
        var head = extractHeader(request);
        var splitHead = head.split(" ", 3);
        return splitHead[1];
    }

    public static String extractRequestProtocol(String request) {
        var head = extractHeader(request);
        var splitHead = head.split(" ", 3);
        return splitHead[2];
    }

    public static String extractHeader(String request) {
        var matcher = Pattern.compile("(.+)").matcher(request);
        return matcher.find() ? matcher.group(1) : "";
    }

    public static Map<String, String> extractHeaderMap(String request) {
        var matcher = Pattern.compile("([ -a-zA-z0-9])+: ([ -a-zA-z0-9])+").matcher(extractHeader(request));
        var headerMap = new HashMap<String, String>();
        while (matcher.find()) {
            var head = matcher.group(0);
            var splitHead = head.split(":", 2);
            headerMap.put(splitHead[0], splitHead[1]);
        }
        return headerMap;
    }

    public static String extractContent(String request) {
        var content = request.split("(?<=\r\n)(\r\n)", 2);
        return content.length == 1 ? "" : content[1];
    }
}
