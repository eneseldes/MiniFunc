
public class Addition extends ArithmeticBinaryExpression {

    // Result of the expression
    Expression value;

    Addition(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    // Getting value
    @Override
    Object getValue() {
        try {
            execute();
            return value.getValue();

        } catch (Exception e) {
            return null;
        }
    }

    // Assigning and calculating
    @Override
    Expression execute() {
        if (leftExpression == null || rightExpression == null) {
            System.out.println("Encountered improper value in " + getClass().getName() + ". Results may be inaccurate!");
            return null;
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

        // If values' data types are not int or double -also string-, return null
        if (!(leftValue instanceof Number) && !(rightValue instanceof Number)) {
            return null;
        }

        /*
            Object values are typcased to Number, since typecasting between int 
            and double is a trouble while looking at the numbers as Object.
        */
        Number resultValue = ((Number) leftValue).doubleValue() + ((Number) rightValue).doubleValue();

        /*
            Assign the field 'value' according to whether resultValue has 
            fractional part then return 'value'
         */
        value = resultValue.doubleValue() % 1 == 0 ? 
                IntegerLiteral.create(resultValue.intValue()) : DoubleLiteral.create(resultValue.doubleValue());
        
        return value.execute();
    }

    @Override
    public String toString() {
        try{
            return "(" + leftExpression.toString() + "+" + rightExpression.toString() + ")";
        }
        catch(Exception e){
            return "**Inexpressible " + getClass().getName() + " result**";
        }
    }

}
