
public class Factorial extends Function {

    Expression expression;
    Integer number;

    public Factorial(Expression expression) {
        this.expression = expression;
    }

    @Override
    Object getValue() {
        return execute().getValue();
    }

    @Override
    Expression execute() {
        try {            
            if (expression.getValue() instanceof Number) {
                this.number = ((Number) expression.getValue()).intValue();                
                if (number < 0) {
                    throw new IllegalArgumentException(" Enter a positive number!!");
                } else if (number % 1 == 0) {
                    if ((boolean) new ConditionalExpression(new IntegerLiteral(number), new IntegerLiteral(1), ConditionalOperator.Greater).getValue()) {
                        return new Multipication(expression, new Factorial(new IntegerLiteral(number - 1))).execute();
                    } else {
                        return new IntegerLiteral(1);
                    }
                } else {
                    throw new IllegalArgumentException(" Invalid type of number entered. Enter a integer!! ");
                }
            } else {
                throw new IllegalArgumentException(" Invalid type of expression entered. Enter a number!! ");
            }
        } catch (Exception e) {
            System.out.println(getClass().getSimpleName() + " " + e);
        }
        return new StringLiteral("");
    }

    @Override
    public String toString() {
        if (expression.getValue() instanceof Number) {
            if (((Number) expression.getValue()).intValue() >= 0) {
                return expression.getValue() + "! = " + getValue();
            }
        }
        return "**Inexpressible " + getClass().getName() + " result** " +execute();
    }

}
