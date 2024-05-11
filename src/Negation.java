
public class Negation extends ArithmeticUnaryExpression {

    // Result of the expression
    Literal value;

    Negation(Expression expression) {
        super(expression);
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
        // If the expression is null, do not assign field 'value' and return null
        if (expression == null) {
            return null;
        }

        /*
            To enhance readability, value of the expression is assigned as 
            'inputValue'. 'resultValue' will be the value that holds the result of 
            the Negation
         */
        Object inputValue = expression.getValue();
        Number resultValue = null;

        // If value's data type is not int or double, return null
        if (!(inputValue instanceof Number)) {
            return null;
        }

        /*
            From now on, only Number objects are left. In the if statement below,
            to prevent 'casting exceptions' from happening, calculations are done
            according to the values' data types
            --Ex: Integer cannot be typecasted to Double
         */
        if (inputValue instanceof Integer) {
            resultValue = -(int) inputValue;
        } else {
            resultValue = -(double) inputValue;
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
        return "-" + expression.toString();
    }

}
