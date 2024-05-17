
// Check Addition.java for comments
public class Negation extends ArithmeticUnaryExpression {

    Negation(Expression expression) {
        super(expression);
    }

    @Override
    Object getValue() {
        try {
            return execute().getValue();

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    Expression execute() {
        // Different from Addition. This class accepts only Number
        if (expression == null) {
            System.out.println("Encountered null expression on " + getClass().getName() + " operation. Results may be inaccurate!");
            return null;
        } else if (!(expression.getValue() instanceof Number)) {
            System.out.println("Encountered non-number expression on " + getClass().getName() + " operation. Results may be inaccurate!");
            return null;
        }

        Number resultValue = -((Number) expression.getValue()).doubleValue();

        return resultValue.doubleValue() % 1 == 0
                ? IntegerLiteral.create(resultValue.intValue()) : DoubleLiteral.create(resultValue.doubleValue());
    }

    @Override
    public String toString() {
        try {
            return "-(" + expression.toString() + ")";
        } catch (Exception e) {
            return "**Inexpressible " + getClass().getName() + " result**";
        }
    }

}
