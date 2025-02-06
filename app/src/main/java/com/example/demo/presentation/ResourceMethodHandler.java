package com.example.demo.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;

abstract public class ResourceMethodHandler {
    protected String method;
    protected String path;

    public ResourceMethodHandler(String method, String path) {
        this.method = method;
        this.path = path;
    }

    public String getRequestKey() {
        return method + " " + path;
    }

    abstract public String handle(String requestStr) throws JsonProcessingException;
}
