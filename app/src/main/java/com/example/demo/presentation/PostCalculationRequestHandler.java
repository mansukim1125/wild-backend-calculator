package com.example.demo.presentation;

import com.example.demo.domain.Calculation;
import com.example.demo.dto.CalculationRequestDto;
import com.example.demo.dto.CalculationResponseDto;
import com.example.demo.persistence.CalculationPersistence;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PostCalculationRequestHandler extends BaseRequestHandler {
    private final ObjectMapper mapper = new ObjectMapper();
    private final CalculationPersistence calculationPersistence;

    public PostCalculationRequestHandler(CalculationPersistence calculationPersistence) {
        super("POST", "/calculation");
        this.calculationPersistence = calculationPersistence;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        InputStream inputStream = exchange.getRequestBody();
        String requestBodyStr = new String(inputStream.readAllBytes());
        inputStream.close();

        CalculationRequestDto requestDto = mapper.readValue(requestBodyStr, CalculationRequestDto.class);

        CalculationResponseDto responseDto = this.calculator(requestDto);

        String responseStr = mapper.writeValueAsString(responseDto);

        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, responseStr.length());

        OutputStream bodyOutputStream = exchange.getResponseBody();
        bodyOutputStream.write(responseStr.getBytes());
        bodyOutputStream.close();
    }

    private CalculationResponseDto calculator(CalculationRequestDto requestDto) {
        CalculationResponseDto responseDto = null;
        if (requestDto.getOperator().equals("+")) {
            responseDto = new CalculationResponseDto(
                requestDto,
                requestDto.getLhs() + requestDto.getRhs()
            );
        } else if (requestDto.getOperator().equals("-")) {
            responseDto = new CalculationResponseDto(
                requestDto,
                requestDto.getLhs() - requestDto.getRhs()
            );
        } else if (requestDto.getOperator().equals("*")) {
            responseDto = new CalculationResponseDto(
                requestDto,
                requestDto.getLhs() * requestDto.getRhs()
            );
        } else if (requestDto.getOperator().equals("/")) {
            responseDto = new CalculationResponseDto(
                requestDto,
                requestDto.getLhs() / requestDto.getRhs()
            );
        }

        if (responseDto != null) {
            Calculation calculation = new Calculation(
                requestDto.getLhs(),
                requestDto.getRhs(),
                requestDto.getOperator(),
                responseDto.getResult()
            );

            this.calculationPersistence.create(calculation);
        }

        return responseDto;
    }
}
