package com.example.demo.application;

public class OperatorSubtract implements Operator {

    @Override
    public int calculate(int lhs, int rhs) {
        return lhs - rhs;
    }
}
