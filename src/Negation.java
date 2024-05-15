
// Check Addition.java for comments
public class Negation extends ArithmeticUnaryExpression {

    Literal value;

    Negation(Expression expression) {
        super(expression);
    }

    @Override
    Object getValue() {
        try {
            execute();
            return value.getValue();

        } catch (NullPointerException e) {
            System.out.println("Encountered null expression on " + getClass().getName() + " operation. Results may be inaccurate!");
        } catch (IllegalArgumentException e) {
            System.out.println("Encountered non-number expression on " + getClass().getName() + " operation. Results may be inaccurate!");
        }
        
        return null;
    }

    @Override
    Expression execute() {
        // Different from Addition. This class accepts only Number
        if (expression == null) {
            throw new NullPointerException();
        }
        else if (!(expression.getValue() instanceof Number)) {
            throw new IllegalArgumentException();
        }

        Object inputValue = expression.getValue();
        Number resultValue = -((Number) inputValue).doubleValue();
        value = resultValue.doubleValue() % 1 == 0
                ? IntegerLiteral.create(resultValue.intValue()) : DoubleLiteral.create(resultValue.doubleValue());

        return value.execute();
    }

    @Override
    public String toString() {
        try {
            return "-" + expression.toString();
        } catch (Exception e) {
            return "**Inexpressible " + getClass().getName() + " result**";
        }
    }

}
