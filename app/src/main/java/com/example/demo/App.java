package com.example.demo;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) throws IOException {
        App app = new App();
        app.run(8080);
    }

    public void run(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 10);

        server.createContext("/calculation", exchange -> {
            String responseStr = "{}";

            Headers responseHeaders = exchange.getResponseHeaders();
            responseHeaders.add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, responseStr.length());

            OutputStream bodyOutputStream = exchange.getResponseBody();
            bodyOutputStream.write(responseStr.getBytes());
            bodyOutputStream.close();
        });

        server.start();
    }
}

