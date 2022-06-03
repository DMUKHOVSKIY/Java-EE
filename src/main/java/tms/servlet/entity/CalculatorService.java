package tms.servlet.entity;

import tms.servlet.storage.InMemoryOperationStorage;

import java.time.LocalDateTime;

public class CalculatorService {
    private final Operation saveOperation = new Operation();
    private InMemoryOperationStorage inMemoryOperationStorage = new InMemoryOperationStorage();

    public double calc(double num1, double num2, String operation, User currentUser) {
        double result = 0;
        switch (operation) {
            case "sum":
                result = num1 + num2;
                break;
            case "diff":
                result = num1 - num2;
                break;
            case "comp":
                result = num1 * num2;
                break;
            case "div":
                result = num1 / num2;
                break;
        }
        saveOperation.setNum1(num1);
        saveOperation.setNum2(num2);
        saveOperation.setOperation(operation);
        saveOperation.setUser(currentUser);
        saveOperation.setDate(LocalDateTime.now());
        inMemoryOperationStorage.save(saveOperation);
        return result;
    }
}
