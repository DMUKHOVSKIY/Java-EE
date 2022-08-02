package org.example;

import org.springframework.stereotype.Component;

@Component
public class CalculatorService {
    public double calculate(double a, double b, String operation) {
        return a + b;
    }
}
