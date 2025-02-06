package com.example.demo.dto;

public class CalculationResponseDto {
    private int result;

    public CalculationResponseDto() {}

    public CalculationResponseDto(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }
}
