package com.example.demo.presentation;

import com.example.demo.persistence.CalculationPersistence;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler implements HttpHandler {

    private final Map<String, ResourceMethodHandler> resourceMethodHandlerMap = new HashMap<>();

    public RequestHandler() {
        CalculationPersistence calculationPersistence = new CalculationPersistence();

        CalculationCreateResource calculationCreateResource = new CalculationCreateResource(calculationPersistence);
        ResourceMethodHandler calculationListResource = new CalculationListResource(calculationPersistence);

        resourceMethodHandlerMap.put(calculationCreateResource.getRequestKey(), calculationCreateResource);
        resourceMethodHandlerMap.put(calculationListResource.getRequestKey(), calculationListResource);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestKey = getRequestKey(
            exchange.getRequestMethod(),
            String.valueOf(exchange.getRequestURI())
        );

        if (!resourceMethodHandlerMap.containsKey(requestKey)) {
            sendResponse(exchange, "{}", 404);
            return;
        }

        ResourceMethodHandler handler = resourceMethodHandlerMap.get(
            getRequestKey(exchange.getRequestMethod(), String.valueOf(exchange.getRequestURI()))
        );

        String requestStr = getRequest(exchange);

        String responseStr = handler.handle(requestStr);
        sendResponse(exchange, responseStr, 200);
    }

    private String getRequestKey(String method, String path) {
        return method + " " + path;
    }

    private String getRequest(HttpExchange exchange) throws IOException {
        InputStream inputStream = exchange.getRequestBody();
        String requestBodyStr = new String(inputStream.readAllBytes());
        inputStream.close();

        return requestBodyStr;
    }

    private void sendResponse(HttpExchange exchange, String responseStr, int statusCode) throws IOException {
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.add("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode, responseStr.length());

        OutputStream bodyOutputStream = exchange.getResponseBody();
        bodyOutputStream.write(responseStr.getBytes());
        bodyOutputStream.close();
    }
}
