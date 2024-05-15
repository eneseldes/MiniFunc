
// Check Addition.java for comments
public class Absolute extends ArithmeticUnaryExpression {

    Literal value;

    Absolute(Expression expression) {
        super(expression);
    }

    @Override
    Object getValue() {
        try {
            execute();
            return value.getValue();

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

        Double inputValue = ((Number) expression.getValue()).doubleValue();
        Number resultValue = inputValue >= 0 ? inputValue : -inputValue;

        value = resultValue.doubleValue() % 1 == 0
                ? IntegerLiteral.create(resultValue.intValue()) : DoubleLiteral.create(resultValue.doubleValue());

        return value.execute();
    }

    @Override
    public String toString() {
        try {
            return "|" + expression.toString() + "|";
        } catch (Exception e) {
            return "**Inexpressible " + getClass().getName() + " result**";
        }
    }

}
