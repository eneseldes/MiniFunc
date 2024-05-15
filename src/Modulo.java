
// Check Addition.java for comments
public class Modulo extends ArithmeticBinaryExpression {

    Literal value;

    Modulo(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
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
        if (leftExpression == null || rightExpression == null) {
            System.out.println("Encountered null expression on " + getClass().getName() + " operation. Results may be inaccurate!");
            return null;
        } else if (!(leftExpression.getValue() instanceof Number) || !(rightExpression.getValue() instanceof Number)) {
            System.out.println("Encountered non-number expression on " + getClass().getName() + " operation. Results may be inaccurate!");
            return null;
        }
        
        Double leftValue = ((Number) leftExpression.getValue()).doubleValue();
        Double rightValue = ((Number) rightExpression.getValue()).doubleValue();
        Number resultValue;
        
        // Find modulo with recursion
        if (leftValue < rightValue) {
            resultValue = leftValue;
        }
        else{
            resultValue = (Number) new Modulo(new DoubleLiteral(leftValue - rightValue), new DoubleLiteral(rightValue)).getValue();
        }

        value = resultValue.doubleValue() % 1 == 0
                ? IntegerLiteral.create(resultValue.intValue()) : DoubleLiteral.create(resultValue.doubleValue());

        return value.execute();
    }

}
