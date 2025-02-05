package com.example.demo.presentation;

import com.example.demo.domain.Calculation;
import com.example.demo.dto.CalculationsResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class GetCalculationsRequestHandler extends BaseRequestHandler {
    private final List<Calculation> calculations = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();

    public GetCalculationsRequestHandler() {
        super("GET", "/calculation");
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        CalculationsResponseDto responseDto = this.getCalculations();

        String responseStr = mapper.writeValueAsString(responseDto);

        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, responseStr.length());

        OutputStream bodyOutputStream = exchange.getResponseBody();
        bodyOutputStream.write(responseStr.getBytes());
        bodyOutputStream.close();
    }

    private CalculationsResponseDto getCalculations() {
        return new CalculationsResponseDto(this.calculations);
    }
}
