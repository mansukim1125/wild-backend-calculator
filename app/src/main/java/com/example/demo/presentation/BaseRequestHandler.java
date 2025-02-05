package com.example.demo.presentation;

import com.sun.net.httpserver.HttpHandler;

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
}
