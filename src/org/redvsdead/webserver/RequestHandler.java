package org.redvsdead.webserver;

import org.redvsdead.webserver.http.HttpResponse;
import org.redvsdead.webserver.http.HttpStatuses;
import org.redvsdead.webserver.util.FileUtils;
import org.redvsdead.webserver.util.StreamUtils;
import org.redvsdead.webserver.http.HttpRequest;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

public class RequestHandler implements Runnable {
    private final HttpRequest request;
    private final OutputStream clientOutputStream;
    private final Socket clientSocket;
    private final String resourcePath;
    private final String protocol;
    private final String NOT_FOUND_PAGE = "/not-found.html";

    public RequestHandler(Socket clientSocket, String protocol) throws IOException {
        this.clientSocket = clientSocket;
        this.clientOutputStream = this.clientSocket.getOutputStream();
        var bufferedReader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        this.request = new HttpRequest(StreamUtils.BufferedReaderToString(bufferedReader));
        var properties = new Properties();
        properties.load(HttpServer.class.getClassLoader().getResourceAsStream(
                "config.properties"));
        this.resourcePath = properties.getProperty("resource.path");
        this.protocol = protocol;
    }

    @Override
    public void run() {
        try {
            this.handleRequest();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            this.close();
        }
    }

    // region Private methods

    private void close() {
        try {
            this.clientOutputStream.close();
            this.clientSocket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void handleRequest() throws IOException {
        var response = this.getResponse();
        try {
        var outputStream = new DataOutputStream(this.clientOutputStream);
            outputStream.writeBytes(response.getHeader());
            outputStream.write(response.getRawContent());
            outputStream.writeBytes("\r\n");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private HttpResponse getResponse() {
        var content = FileUtils.readFile(request.getPath());
        return content.length > 0
                ? new HttpResponse(protocol, HttpStatuses.OK.code, content)
                : new HttpResponse(protocol, HttpStatuses.NOT_FOUND.code, FileUtils.readFile(NOT_FOUND_PAGE));
    }

    // endregion
}
