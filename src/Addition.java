
public class Addition extends ArithmeticBinaryExpression {

    Addition(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    // Getting value
    @Override
    Object getValue() {
        try {
            return execute().getValue();
        } catch (Exception e) {
            return null;
        }
    }

    // Assigning and calculating
    @Override
    Expression execute() {
        // If one of the expression is null, do not assign field 'value' and return null
        if (leftExpression == null || rightExpression == null) {
            System.out.println("Encountered null expression on " + getClass().getSimpleName() + " operation. Results may be inaccurate!");
            return null;
        }

        Object leftValue = leftExpression.getValue();
        Object rightValue = rightExpression.getValue();

        // If one of the value is String, do concatenation
        if (leftValue instanceof String || rightValue instanceof String)
            return StringLiteral.create("" + leftValue + rightValue);
        

        // If values' data types are not int or double -also string-, throw exception
        if (!(leftExpression.getValue() instanceof Number) || !(rightExpression.getValue() instanceof Number)) {
            System.out.println("Encountered non-number expression on " + getClass().getSimpleName() + " operation. Results may be inaccurate!");
            return null;
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
        return resultValue.doubleValue() % 1 == 0
                ? IntegerLiteral.create(resultValue.intValue()) : DoubleLiteral.create(resultValue.doubleValue());
    }

    // Overrode in this class because Addition should also accept String input
    @Override
    public String toString() {
        if (leftExpression == null || rightExpression == null) {
            return "??Inexpressible " + getClass().getSimpleName() + " result due to null value??";
        }
        
        // Print result correctly, just when both left and right expressions are String OR Number
        if ((leftExpression.getValue() instanceof Number || leftExpression.getValue() instanceof String)
                && (rightExpression.getValue() instanceof Number ||  rightExpression.getValue() instanceof String)) {
            return "(" + leftExpression.toString() + "+" + rightExpression.toString() + ")";
        }
        
        return "((Improper Calculation Here...)" + leftExpression.toString() + "+" + rightExpression.toString() + ")";
    }

}
