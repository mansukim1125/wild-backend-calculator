package com.example.demo.presentation;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

abstract public class BaseRequestHandler implements HttpHandler {
    protected String method;
    protected String path;

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public BaseRequestHandler(String method, String path) {
        this.method = method;
        this.path = path;
    }

    protected String getRequestBody(HttpExchange exchange) throws IOException {
        InputStream inputStream = exchange.getRequestBody();
        String requestBodyStr = new String(inputStream.readAllBytes());
        inputStream.close();

        return requestBodyStr;
    }

    protected void response(HttpExchange exchange, String responseStr, int statusCode) throws IOException {
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.add("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode, responseStr.length());

        OutputStream bodyOutputStream = exchange.getResponseBody();
        bodyOutputStream.write(responseStr.getBytes());
        bodyOutputStream.close();
    }
}
