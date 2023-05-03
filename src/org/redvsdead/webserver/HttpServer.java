package org.redvsdead.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

public class HttpServer implements Runnable{
    private final int portNumber;
    private final int threadNumber;
    private final int timeOut;
    private final String protocol;

    public HttpServer(int portNumber, int threadNumber, int timeOut, String protocol) {
        this.portNumber = portNumber;
        this.threadNumber = threadNumber;
        this.timeOut = timeOut;
        this.protocol = protocol;
    }

    public void run() {
        try {
            var socket = new ServerSocket(portNumber);
            var executor = Executors.newFixedThreadPool(threadNumber);
            while (true) {
                var socketClient = socket.accept();
                socketClient.setSoTimeout(timeOut);
                executor.submit(new RequestHandler(socketClient, protocol));
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
