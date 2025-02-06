package com.example.demo.presentation;

import com.example.demo.dto.CalculationsResponseDto;
import com.example.demo.persistence.CalculationPersistence;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CalculationListResource extends ResourceMethodHandler {
    private final ObjectMapper mapper = new ObjectMapper();
    private final CalculationPersistence calculationPersistence;

    public CalculationListResource(CalculationPersistence calculationPersistence) {
        super("GET", "/calculations");
        this.calculationPersistence = calculationPersistence;
    }

    @Override
    public String handle(String requestStr) throws JsonProcessingException {
        CalculationsResponseDto responseDto = this.getCalculations();

        return mapper.writeValueAsString(responseDto);
    }

    private CalculationsResponseDto getCalculations() {
        return new CalculationsResponseDto(this.calculationPersistence.findAll());
    }
}
