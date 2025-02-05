package com.example.demo.persistence;

import com.example.demo.domain.Calculation;

import java.util.ArrayList;
import java.util.List;

public class CalculationPersistence {
    private final List<Calculation> calculations = new ArrayList<>();

    public void create(Calculation calculation) {
        this.calculations.add(calculation);
    }

    public List<Calculation> findAll() {
        return this.calculations;
    }
}
