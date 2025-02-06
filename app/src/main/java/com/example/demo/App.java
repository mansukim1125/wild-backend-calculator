package com.example.demo;

import com.example.demo.presentation.RequestHandler;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App {

    public static void main(String[] args) throws IOException {
        App app = new App();
        app.run(8080);
    }

    public void run(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 10);

        HttpHandler handler = new RequestHandler();
        server.createContext("/", handler);

        server.start();
    }
}
