
public class PrimeNumber extends Function {

    Expression number;
    private Double divisor = 2.0;
    private Boolean isPrime;
    private Double numberDouble;

    PrimeNumber(Expression number) {
        this.number = number;
        if (number.getValue() instanceof Number) {
            numberDouble = ((Number) number.getValue()).doubleValue();
        } else {
           throw new IllegalArgumentException(" Invalid type of expression entered. Enter a Number!! ");
        }
    }

    private PrimeNumber(Double numberDouble, Double divisor) {
        this.divisor = divisor;
        this.numberDouble = numberDouble;
    }

    @Override
    Object getValue() {
        return execute().getValue();
    }

    @Override
    Expression execute() {

        if (numberDouble % 1 != 0) {
            return new BooleanLiteral(false);
        }
        if (divisor < numberDouble) {
            if (numberDouble % divisor == 0) {
                return new BooleanLiteral(false);
            } else {
                return new PrimeNumber(numberDouble, (divisor + 1.0)).execute();
            }
        } else if (numberDouble == 1) {
            return new BooleanLiteral(false);
        }
        return new BooleanLiteral(true);

    }

    public String toString() {
        return "Is " + number + " prime?: " + (boolean) getValue();
    }
}
