package org.redvsdead.webserver.http;

public class HttpResponse {
    private final String protocol;
    private final int statusCode;
    private final String status;
    private final byte[] rawContent;
    private final String contentType;
    private final int contentLength;
    private final String defaultContentType = "text/html;charset=utf-8";

    public HttpResponse(String protocol, int statusCode, byte[] content) {
        this.protocol = protocol;
        this.statusCode = statusCode;
        this.status = this.getStatus();
        this.rawContent = content;
        this.contentType = defaultContentType;
        this.contentLength = content.length;
    }

    public HttpResponse(String protocol, int statusCode, byte[] content, String contentType) {
        this.protocol = protocol;
        this.statusCode = statusCode;
        this.status = this.getStatus();
        this.rawContent = content;
        this.contentType = contentType;
        this.contentLength = content.length;
    }

    public String getHeader() {
        var sb = new StringBuilder();
        sb.append(this.protocol + " " + this.statusCode + " ");
        if (this.status.length() > 0) {
            sb.append(this.status + " ");
        }
        sb.append("\r\n");
        sb.append("Content-Type: " + this.contentType + "\r\n");
        sb.append("Content-Length: " + this.contentLength + "\r\n");
        sb.append("\r\n");
        return sb.toString();
    }

    public byte[] getRawContent() {
        return this.rawContent;
    }

    private String getStatus() {
        return switch (this.statusCode) {
            case 200 -> "OK";
            case 302 -> "Found";
            default -> "";
        };
    }
}
