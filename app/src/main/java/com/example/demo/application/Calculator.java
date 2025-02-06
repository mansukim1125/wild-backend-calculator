package com.example.demo.application;

import com.example.demo.domain.Calculation;
import com.example.demo.dto.CalculationRequestDto;

import java.util.HashMap;
import java.util.Map;

public class Calculator {

    private final Map<String, Operator> operators = new HashMap<>();

    public Calculator() {
        operators.put("+", new OperatorAdd());
        operators.put("-", new OperatorSubtract());
        operators.put("*", new OperatorMultiply());
        operators.put("/", new OperatorDivide());
    }

    public Calculation calculate(CalculationRequestDto requestDto) {
        Operator operator = operators.get(requestDto.getOperator());

        int result = operator.calculate(requestDto.getLhs(), requestDto.getRhs());

        return new Calculation(requestDto.getLhs(), requestDto.getRhs(), requestDto.getOperator(), result);
    }
}
