
public class XorExpression extends Expression {

    Expression x1;
    Expression x2;

    XorExpression(Expression x1, Expression x2) {
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
        if (x1.getValue() == x2.getValue()) {
            return new BooleanLiteral(false);
        }
        return new BooleanLiteral(true);
    }

    @Override
    public String toString() {
        return "( " + x1 + " xor " + x2 + " ) = " +getValue();
    }

}
