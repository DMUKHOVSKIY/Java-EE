package org.example;

import org.springframework.stereotype.Component;

@Component
public class Invoker {

    private CalculatorService calculatorService;

    public Invoker(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    public void invoke(){
        double sum = calculatorService.calculate(2,2,"sum");
        System.out.println(sum);
    }
}
