package com.example.demo.dto;

public class CalculationRequestDto {
    private String operator;
    private int lhs;
    private int rhs;

    public CalculationRequestDto() {}

    public CalculationRequestDto(String operator, int lhs, int rhs) {
        this.operator = operator;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public int getLhs() {
        return lhs;
    }

    public String getOperator() {
        return operator;
    }

    public int getRhs() {
        return rhs;
    }
}
