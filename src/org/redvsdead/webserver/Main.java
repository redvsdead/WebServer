package org.redvsdead.webserver;

import java.io.IOException;
import java.util.Properties;
import java.util.Timer;
import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args) throws IOException {
        var properties = new Properties();
        properties.load(Main.class.getResourceAsStream("/config.properties"));
        var portNumber = Integer.parseInt(properties.getProperty("http.server.port"));
        var threadNumber = Integer.parseInt(properties.getProperty("thread.pool.number"));
        var timeOut = Integer.parseInt(properties.getProperty("response.timeout"));
        var protocol = properties.getProperty("protocol");
        var server = new Thread(new HttpServer(portNumber, threadNumber, timeOut, protocol));
        server.run();
    }
}