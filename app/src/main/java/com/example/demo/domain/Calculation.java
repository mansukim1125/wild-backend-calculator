package com.example.demo.domain;

public class Calculation {
    private int lhs;
    private int rhs;
    private String operator;
    private int result;

    public Calculation(int lhs, int rhs, String operator, int result) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.operator = operator;
        this.result = result;
    }

    public int getLhs() {
        return lhs;
    }

    public int getRhs() {
        return rhs;
    }

    public String getOperator() {
        return operator;
    }

    public int getResult() {
        return result;
    }
}
