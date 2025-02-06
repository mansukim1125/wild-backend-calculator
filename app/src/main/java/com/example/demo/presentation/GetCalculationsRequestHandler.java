package com.example.demo.presentation;

import com.example.demo.dto.CalculationsResponseDto;
import com.example.demo.persistence.CalculationPersistence;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

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

        this.response(exchange, responseStr, 200);
    }

    private CalculationsResponseDto getCalculations() {
        return new CalculationsResponseDto(this.calculationPersistence.findAll());
    }
}
