
public class Addition extends ArithmeticBinaryExpression {

    Variable value;

    Addition(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    @Override
    Object getValue() {
        execute();
        return value.getValue();
    }

    @Override
    Expression execute() {
        Object leftValue = leftExpression.getValue();
        Object rightValue = rightExpression.getValue();
        Number resultValue = null;

        if (!(leftValue instanceof Number) && !(rightValue instanceof Number)) {
            return null;
        }

        if (leftValue instanceof Integer && rightValue instanceof Integer) {
            resultValue = (int) leftValue + (int) rightValue;
        } else if (leftValue instanceof Double || rightValue instanceof Integer) {
            double e1 = leftValue instanceof Integer ? (int) leftValue : (double) leftValue;
            double e2 = rightValue instanceof Integer ? (int) rightValue : (double) rightValue;
            
            resultValue = e1 + e2;
        }

        if (resultValue.doubleValue() % 1 == 0) {
            value = new IntegerVariable("resultValue", resultValue.intValue());
            return value.execute();
        } else {
            value = new DoubleVariable("resultValue", resultValue.doubleValue());
            return value.execute();
        }
    }

    @Override
    public String toString() {
        return "(" + leftExpression.toString() + "+" + rightExpression.toString() + ")";
    }

}
