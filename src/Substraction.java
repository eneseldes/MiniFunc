
// Check Addition.java for comments
public class Substraction extends ArithmeticBinaryExpression {

    Substraction(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
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
        if (leftExpression == null || rightExpression == null) {
            System.out.println("Encountered null expression on " + getClass().getName() + " operation. Results may be inaccurate!");
            return null;
        } else if (!(leftExpression.getValue() instanceof Number) || !(rightExpression.getValue() instanceof Number)) {
            System.out.println("Encountered non-number expression on " + getClass().getName() + " operation. Results may be inaccurate!");
            return null;
        }

        Number resultValue = ((Number) leftExpression.getValue()).doubleValue() - ((Number) rightExpression.getValue()).doubleValue();

        return resultValue.doubleValue() % 1 == 0
                ? IntegerLiteral.create(resultValue.intValue()) : DoubleLiteral.create(resultValue.doubleValue());
    }

    @Override
    public String toString() {
        try {
            return leftExpression.toString() + "-" + rightExpression.toString();
        } catch (Exception e) {
            return "**Inexpressible " + getClass().getName() + " result**";
        }
    }

}
