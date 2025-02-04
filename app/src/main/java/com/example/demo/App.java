package com.example.demo;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) throws IOException {
        App app = new App();
        app.run(8080);
    }

    public void run(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 1);
        server.start();
    }
}
