package com.example.demo.dto;

import com.example.demo.domain.Calculation;

public class CalculationsResponseDto {
    private Calculation calculations;

    public CalculationsResponseDto() {}

    public CalculationsResponseDto(Calculation calculations) {
        this.calculations = calculations;
    }

    public Calculation getCalculations() {
        return calculations;
    }
}
