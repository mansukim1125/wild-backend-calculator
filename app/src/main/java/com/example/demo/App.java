package com.example.demo;

import com.example.demo.dto.CalculationRequestDto;
import com.example.demo.dto.CalculationResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {
    private final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        App app = new App();
        app.run(8080);
    }

    public void run(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 10);

        server.createContext("/calculation", exchange -> {
            String responseStr = "{}";

            String requestMethod = exchange.getRequestMethod();

            if (requestMethod.equals("POST")) {
                InputStream inputStream = exchange.getRequestBody();
                String requestBodyStr = new String(inputStream.readAllBytes());
                inputStream.close();

                CalculationRequestDto requestDto = mapper.readValue(requestBodyStr, CalculationRequestDto.class);

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
                }

                responseStr = mapper.writeValueAsString(responseDto);
            }


            Headers responseHeaders = exchange.getResponseHeaders();
            responseHeaders.add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, responseStr.length());

            OutputStream bodyOutputStream = exchange.getResponseBody();
            bodyOutputStream.write(responseStr.getBytes());
            bodyOutputStream.close();
        });

        server.start();
    }
}

