
public class Factorial extends Function {

    private Expression expression;
    private Integer number;

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
            //Here the types of expression is checked.If expression's type is not a number, exception is thrown
            if (expression.getValue() instanceof Number) {
                this.number = ((Number) expression.getValue()).intValue();
                if (number < 0) {
                    throw new IllegalArgumentException(" Enter a positive number!!");
                    //Here the types of expression is checked.If expression's value is a integer number, program will continue
                } else if ((Boolean) new Condition(new Modulo(new IntegerLiteral(number), new IntegerLiteral(1)), new IntegerLiteral(0), ConditionalOperator.Equal).getValue()) {
                    //If number > 1 new Factorial is returned and multiply with expression's value
                    if ((boolean) new Condition(new IntegerLiteral(number), new IntegerLiteral(1), ConditionalOperator.Greater).getValue()) {
                        return new Multipication(expression, new Factorial(new IntegerLiteral(number - 1))).execute();
                    }//If number = 1, 1 is returned 
                    else {
                        return new IntegerLiteral(1);
                    }
                } //If expression's type is a double number, exception is thrown
                else {
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
        return "**Inexpressible " + getClass().getName() + " result** " + execute();
    }

}
