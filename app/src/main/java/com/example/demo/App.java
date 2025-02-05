package com.example.demo;

import com.example.demo.persistence.CalculationPersistence;
import com.example.demo.presentation.BaseRequestHandler;
import com.example.demo.presentation.GetCalculationsRequestHandler;
import com.example.demo.presentation.PostCalculationRequestHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class App {
    private final Map<String, BaseRequestHandler> requestHandlerMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        App app = new App();
        app.run(8080);
    }

    public void run(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 10);

        CalculationPersistence calculationPersistence = new CalculationPersistence();

        BaseRequestHandler postCalculationRequestHandler = new PostCalculationRequestHandler(calculationPersistence);
        BaseRequestHandler getCalculationsRequestHandler = new GetCalculationsRequestHandler(calculationPersistence);

        requestHandlerMap.put(
            postCalculationRequestHandler.getMethod() + " " +
            postCalculationRequestHandler.getPath(),
            postCalculationRequestHandler
        );
        requestHandlerMap.put(
            getCalculationsRequestHandler.getMethod() + " " +
            getCalculationsRequestHandler.getPath(),
            getCalculationsRequestHandler
        );

        this.requestHandler(server);

        server.start();
    }

    private void requestHandler(HttpServer server) {
        server.createContext("/", exchange -> {
            BaseRequestHandler requestHandler = requestHandlerMap.get(exchange.getRequestMethod() + " " + exchange.getRequestURI());
            requestHandler.handle(exchange);
        });
    }
}

