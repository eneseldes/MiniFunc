
public class Factorial extends ArithmeticUnaryExpression {

    Integer number;

    public Factorial(Expression expression) {
        super(expression);
        if (expression.getValue() instanceof Number) {
            if (((Number) expression.getValue()).intValue() % 1 == 0) {
                this.number = ((Number) expression.getValue()).intValue();
            } else {
                throw new IllegalArgumentException(" Invalid type of number entered. Enter a integer!! ");
            }

        } else {
            throw new IllegalArgumentException(" Invalid type of expression entered. Enter a number!! ");
        }
    }

    @Override
    Object getValue() {
        return execute().getValue();
    }

    @Override
    Expression execute() {

        if (number > 1) {
            return new Multipication(expression, new Factorial(new IntegerLiteral(number - 1)).execute());

        } else {
            return new IntegerLiteral(1);
        }

    }

    @Override
    public String toString() {
        return expression.getValue() + "! = " + getValue();
    }

}
