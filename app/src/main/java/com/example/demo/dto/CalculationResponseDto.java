package com.example.demo.dto;

public class CalculationResponseDto {
    private CalculationRequestDto requestData;
    private int result;

    public CalculationResponseDto() {}

    public CalculationResponseDto(CalculationRequestDto requestData, int result) {
        this.requestData = requestData;
        this.result = result;
    }

    public CalculationRequestDto getRequestData() {
        return requestData;
    }

    public int getResult() {
        return result;
    }
}
