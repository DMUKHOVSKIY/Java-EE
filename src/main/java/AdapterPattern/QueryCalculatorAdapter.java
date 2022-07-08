package AdapterPattern;

public class QueryCalculatorAdapter {
    public static Operation adapt(String qwery){
        String[] str = qwery.split(" ");
        Operation op = new Operation.Builder()
                .num1(Double.parseDouble(str[0]))
                .num2(Double.parseDouble(str[1]))
                .build();
        return op;
    }
}
