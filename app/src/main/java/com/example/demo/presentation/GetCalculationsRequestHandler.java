package com.example.demo.presentation;

import com.example.demo.dto.CalculationsResponseDto;
import com.example.demo.persistence.CalculationPersistence;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class GetCalculationsRequestHandler extends BaseRequestHandler {
    private final ObjectMapper mapper = new ObjectMapper();
    private final CalculationPersistence calculationPersistence;

    public GetCalculationsRequestHandler(CalculationPersistence calculationPersistence) {
        super("GET", "/calculation");
        this.calculationPersistence = calculationPersistence;
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
        return new CalculationsResponseDto(this.calculationPersistence.findAll());
    }
}
