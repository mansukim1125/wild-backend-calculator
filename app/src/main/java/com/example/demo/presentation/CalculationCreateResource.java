package com.example.demo.presentation;

import com.example.demo.application.Calculator;
import com.example.demo.domain.Calculation;
import com.example.demo.dto.CalculationRequestDto;
import com.example.demo.dto.CalculationResponseDto;
import com.example.demo.persistence.CalculationPersistence;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CalculationCreateResource extends ResourceMethodHandler {
    private final ObjectMapper mapper = new ObjectMapper();
    private final CalculationPersistence calculationPersistence;

    private final Calculator calculator = new Calculator();

    public CalculationCreateResource(CalculationPersistence calculationPersistence) {
        super("POST", "/calculations");
        this.calculationPersistence = calculationPersistence;
    }

    @Override
    public String handle(String requestStr) throws JsonProcessingException {
        CalculationRequestDto requestDto = mapper.readValue(requestStr, CalculationRequestDto.class);

        Calculation calculation = this.calculator.calculate(requestDto);

        this.calculationPersistence.create(calculation);

        CalculationResponseDto responseDto = new CalculationResponseDto(calculation.getResult());

        return mapper.writeValueAsString(responseDto);
    }
}
