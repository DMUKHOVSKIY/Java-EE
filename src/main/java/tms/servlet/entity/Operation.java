package tms.servlet.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Operation {
    private double num1;
    private double num2;
    private String operation;
    private User user;
    private LocalDateTime date;

    public double getNum1() {
        return num1;
    }

    public void setNum1(double num1) {
        this.num1 = num1;
    }

    public double getNum2() {
        return num2;
    }

    public void setNum2(double num2) {
        this.num2 = num2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "num1=" + num1 +
                ", num2=" + num2 +
                ", operation='" + operation + '\'' +
                ", user=" + user +
                ", date=" + date +
                '}';
    }
}
