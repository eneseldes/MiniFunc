
// Check Addition.java for comments
public class Absolute extends ArithmeticUnaryExpression {

    Absolute(Expression expression) {
        super(expression);
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
        if (expression == null) {
            System.out.println("Encountered null expression on " + getClass().getName() + " operation. Results may be inaccurate!");
            return null;
        } else if (!(expression.getValue() instanceof Number)) {
            System.out.println("Encountered non-number expression on " + getClass().getName() + " operation. Results may be inaccurate!");
            return null;
        }

        Double inputValue = ((Number) expression.getValue()).doubleValue();
        Number resultValue = inputValue >= 0 ?
                inputValue : (Number)new Negation(expression).getValue();

        return resultValue.doubleValue() % 1 == 0
                ? IntegerLiteral.create(resultValue.intValue()) : DoubleLiteral.create(resultValue.doubleValue());
    }

    @Override
    public String toString() {
        if (expression == null) {
            return "??Inexpressible " + getClass().getName() + " result due to null value??";
        }
        if (!(expression.getValue() instanceof Number)) {
            return "((Improper Calculation Here...) -" + expression.toString() + ")";
        }
        
        return "(-" + expression.toString() + ")";
    }

}
