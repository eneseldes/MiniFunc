
public class Addition extends ArithmeticBinaryExpression {

    // Result of the expression
    Literal value;

    Addition(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    // Getting value
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

    // Assigning and calculating
    @Override
    Expression execute() {
        // If one of the expression is null, do not assign field 'value' and return null
        if (leftExpression == null || rightExpression == null) {
            throw new NullPointerException();
        }

        /*
            To enhance readability, values of the left and right expressions are
            assigned as 'leftValue' and 'rightValue'. 'resultValue' will be the
            value that holds the result of the Addition
         */
        Object leftValue = leftExpression.getValue();
        Object rightValue = rightExpression.getValue();

        // If one of the value is String, do concatenation
        if (leftValue instanceof String || rightValue instanceof String) {
            value = StringLiteral.create("" + leftValue + rightValue);
            return value.execute();
        }

        // If values' data types are not int or double -also string-, throw exception
        if (!(leftValue instanceof Number) || !(rightValue instanceof Number)) {
            throw new IllegalArgumentException();
        }

        /*
            Object values are typcased to Number, since typecasting between int 
            and double is a trouble while looking at the numbers as Object.
         */
        Number resultValue = ((Number) leftValue).doubleValue() + ((Number) rightValue).doubleValue();

        /*
            Assign the field 'value' according to whether resultValue has 
            fractional part then execute
         */
        value = resultValue.doubleValue() % 1 == 0
                ? IntegerLiteral.create(resultValue.intValue()) : DoubleLiteral.create(resultValue.doubleValue());

        return value.execute();
    }

    @Override
    public String toString() {
        try {
            return "(" + leftExpression.toString() + "+" + rightExpression.toString() + ")";
        } catch (Exception e) {
            return "**Inexpressible " + getClass().getName() + " result**";
        }
    }

}
