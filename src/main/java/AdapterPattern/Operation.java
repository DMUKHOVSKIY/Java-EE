package AdapterPattern;

import java.sql.Date;

public class Operation {
    private double num1;
    private double num2;
    private String operation;
    private double result;
    private User user;
    private int id;
    private Date date;

    public static class Builder {
        private final Operation operat;

        public Builder() {
            operat = new Operation();
        }

        public Builder num1(double num1) {
            operat.num1 = num1;
            return this;
        }

        public Builder num2(double num2) {
            operat.num2 = num2;
            return this;
        }

        public Builder operation(String operation) {
            operat.operation = operation;
            return this;
        }

        public Builder result(double result) {
            operat.result = result;
            return this;
        }

        public Builder user(User user) {
            operat.user = user;
            return this;
        }

        public Builder id(int id) {
            operat.id = id;
            return this;
        }

        public Builder date(Date date) {
            operat.date = date;
            return this;
        }

        public Operation build() {
            return operat;
        }
    }

    public double getNum1() {
        return num1;
    }

    public double getNum2() {
        return num2;
    }

    public String getOperation() {
        return operation;
    }

    public double getResult() {
        return result;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "adapter.Operation{" +
                "num1=" + num1 +
                ", num2=" + num2 +
                ", operation='" + operation + '\'' +
                ", result='" + result + '\'' +
                ", user=" + user +
                ", date=" + date +
                ", id=" + id +
                '}';
    }
}


