package org.redvsdead.webserver.http;

public enum HttpStatuses {
    OK(200),
    BAD_REQUEST(400),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500),
    SERVICE_UNAVAILABLE(503);

    public final int code;

    private HttpStatuses(int code) {
        this.code = code;
    }
}
