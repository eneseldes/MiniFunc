
public class Multipication extends ArithmeticBinaryExpression {

    // Result of the expression
    Literal value;

    Multipication(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    // Getting value
    @Override
    Object getValue() {
        try {
            execute();
            return value.getValue();

        } catch (NullPointerException e) {
            return null;
        }
    }

    // Assigning and calculating
    @Override
    Expression execute() {
        // If one of the expression is null, do not assign field 'value' and return null
        if (leftExpression == null || rightExpression == null) {
            return null;
        }

        /*
            To enhance readability, values of the left and right expressions are
            assigned as 'leftValue' and 'rightValue'. 'resultValue' will be the
            value that holds the result of the Addition
         */
        Object leftValue = leftExpression.getValue();
        Object rightValue = rightExpression.getValue();
        Number resultValue = null;

        // If values' data types are not int or double, return null
        if (!(leftValue instanceof Number) && !(rightValue instanceof Number)) {
            return null;
        }

        /*
            From now on, only Number objects are left. In the if statement below,
            to prevent 'casting exceptions' from happening, calculations are done
            according to the values' data types
            --Ex: Integer cannot be typecasted to Double
        */
        if (leftValue instanceof Integer && rightValue instanceof Integer) {
            resultValue = (int) leftValue * (int) rightValue;
        } else {
            double e1 = leftValue instanceof Integer ? (int) leftValue : (double) leftValue;
            double e2 = rightValue instanceof Integer ? (int) rightValue : (double) rightValue;

            resultValue = e1 * e2;
        }

        /*
            Assign the field 'value' according to whether resultValue has 
            fractional part then execute
        */
        if (resultValue.doubleValue() % 1 == 0) {
            value = IntegerLiteral.create(resultValue.intValue());
            return value.execute();
        } else {
            value = DoubleLiteral.create(resultValue.doubleValue());
            return value.execute();
        }
    }

    @Override
    public String toString() {
        return "(" + leftExpression.toString() + "*" + rightExpression.toString() + ")";
    }

}
