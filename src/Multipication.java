
// Check Addition.java for comments
public class Multipication extends ArithmeticBinaryExpression {

    Literal value;

    Multipication(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
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
        if (leftExpression == null || rightExpression == null) {
            throw new NullPointerException();
        }
        else if (!(leftExpression.getValue() instanceof Number) || !(rightExpression.getValue() instanceof Number)) {
            throw new IllegalArgumentException();
        }

        Object leftValue = leftExpression.getValue();
        Object rightValue = rightExpression.getValue();
        Number resultValue = ((Number) leftValue).doubleValue() * ((Number) rightValue).doubleValue();

        value = resultValue.doubleValue() % 1 == 0
                ? IntegerLiteral.create(resultValue.intValue()) : DoubleLiteral.create(resultValue.doubleValue());

        return value.execute();
    }

    @Override
    public String toString() {
        try {
            return "(" + leftExpression.toString() + "*" + rightExpression.toString() + ")";
        } catch (Exception e) {
            return "**Inexpressible " + getClass().getName() + " result**";
        }
    }

}
