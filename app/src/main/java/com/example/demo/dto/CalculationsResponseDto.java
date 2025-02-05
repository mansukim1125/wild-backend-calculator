package com.example.demo.dto;

import com.example.demo.domain.Calculation;

import java.util.List;

public class CalculationsResponseDto {
    private List<Calculation> calculations;

    public CalculationsResponseDto() {}

    public CalculationsResponseDto(List<Calculation> calculations) {
        this.calculations = calculations;
    }

    public List<Calculation> getCalculations() {
        return calculations;
    }
}
