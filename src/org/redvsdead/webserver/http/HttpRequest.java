package org.redvsdead.webserver.http;

import java.util.Map;

public class HttpRequest {
    private final String method;
    private final String path;
    private final String protocol;
    private final Map<String, String> headers;
    private final String rawRequest;
    private final String content;

    public HttpRequest(String rawRequest) {
        this.rawRequest = rawRequest;
        this.method = HttpRequestParser.extractRequestMethod(rawRequest);
        this.path = HttpRequestParser.extractRequestPath(rawRequest);
        this.protocol = HttpRequestParser.extractRequestProtocol(rawRequest);
        this.headers = HttpRequestParser.extractHeaderMap(rawRequest);
        this.content = HttpRequestParser.extractContent(rawRequest);
    }

    // region Getters
    public String getRawRequest() {
        return this.rawRequest;
    }

    public String getContent() {
        return this.content;
    }

    public String getMethod() {
        return this.method;
    }

    public String getPath() {
        return this.path;
    }

    public String getProtocol() {
        return this.protocol;
    }

    // endregion
}
