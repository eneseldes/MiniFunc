
public class IsPrime extends Function {

    private Expression number;
    private Integer divisor = 2;
    private Boolean isPrime;
    private Double numberDouble;

    IsPrime(Expression number) {
        this.number = number;
        if (number.getValue() instanceof Number) {
            numberDouble = ((Number) number.getValue()).doubleValue();
        }
    }

    private IsPrime(Double numberDouble, Integer divisor, Expression number) {
        //This constructor is being used to do recursion
        this.number = number;
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
            //Here the type of expression is checked.If expression's type is a boolean, exception is thrown
            if (number.getValue() instanceof Number) {
                //If number is a double number,like 4.2, false is returned
                if (numberDouble % 1 != 0) {
                    return new BooleanLiteral(false);
                }
                //We check if divisor is smaller than the number
                if ((Boolean) new Condition(new IntegerLiteral(divisor), new DoubleLiteral(numberDouble), ConditionalOperator.Less).getValue()) {
                    //If the remainder is equal to 0, it means that it is not the prime number, so we return false
                    if (numberDouble % divisor == 0) {
                        return new BooleanLiteral(false);
                    } //If it is not we return new Prime Number
                    else {
                        return new IsPrime(numberDouble, (Integer) new Addition(new DoubleLiteral(divisor),
                                new DoubleLiteral(1.0)).getValue(), number).execute();
                    }
                } //We checked here if the number is less equal than 1 so it is not prime number
                else if ((Boolean) new Condition(new DoubleLiteral(numberDouble), new IntegerLiteral(1), ConditionalOperator.LessEqual).getValue()) {
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
        if (number.getValue() instanceof Number) {
            return "Is " + number.getValue() + " prime? " + getValue();
        }
        return getValue().toString();
    }
}
