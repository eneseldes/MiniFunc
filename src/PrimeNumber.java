
public class PrimeNumber extends Function {

    Expression number;
    private Integer divisor = 2;
    private Boolean isPrime;
    private Double numberDouble;

    PrimeNumber(Expression number) {
        this.number = number;
        if (number.getValue() instanceof Number) {

            numberDouble = ((Number) number.getValue()).doubleValue();

        }
    }

    private PrimeNumber(Double numberDouble, Integer divisor) {
        this.divisor = divisor;
        this.numberDouble = numberDouble;
    }

    @Override
    Object getValue() {
        return execute().getValue();
    }

    @Override
    Expression execute() {
        try {
            if (number.getValue() instanceof Number) {
                if (numberDouble % 1 != 0) {
                    return new BooleanLiteral(false);
                }

                if ((Boolean) new ConditionalExpression(new IntegerLiteral(divisor), new DoubleLiteral(numberDouble), ConditionalOperator.Less).getValue()) {
                    if (numberDouble % divisor == 0) {
                        return new BooleanLiteral(false);
                    } else {
                        return new PrimeNumber(numberDouble, (Integer) new Addition(new DoubleLiteral(divisor),
                                new DoubleLiteral(1.0)).getValue()).execute();
                    }
                } else if (numberDouble == 1) {
                    return new BooleanLiteral(false);
                }
                return new BooleanLiteral(true);
            } else {
                throw new IllegalArgumentException(" Invalid type of expression entered. Enter a Number!! ");
            }
        } catch (Exception e) {
            System.out.println(getClass().getSimpleName() + " " + e);
        }
        return new StringLiteral("");

    }

    public String toString() {
        if(number.getValue() instanceof Number){
            return "Is " +number.getValue() +" is prime? " +getValue();
        }
        return getValue().toString();
    }
}
