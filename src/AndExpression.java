
public class AndExpression extends Expression {

    Expression x1;
    Expression x2;

    AndExpression(Expression x1, Expression x2) {
        if (x1.getValue() instanceof Boolean && x2.getValue() instanceof Boolean) {

            this.x1 = x1;
            this.x2 = x2;
        } else {
            throw new IllegalArgumentException(" Invalid type of expression entered. Enter a boolean!! ");
        }
    }

    @Override
    Object getValue() {
        return execute().getValue();
    }

    @Override
    Expression execute() {
        return new BooleanLiteral((boolean) x1.getValue() && (boolean) x2.getValue());

    }

    @Override
    public String toString() {
        return "( " + x1 + " and " + x2 + " ) = " +getValue();
    }

}
