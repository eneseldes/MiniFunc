
public class Max extends Function {

    private Expression leftExpression;
    private Expression rightExpression;

    Max(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
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
        if (leftExpression == null || rightExpression == null) {
            System.out.println("Encountered null expression on " + getClass().getSimpleName() + " operation. Results may be inaccurate!");
            return null;
        } else if (!(leftExpression.getValue() instanceof Number) || !(rightExpression.getValue() instanceof Number)) {
            System.out.println("Encountered non-number expression on " + getClass().getSimpleName() + " operation. Results may be inaccurate!");
            return null;
        }

        return new If(
                new Condition(leftExpression, rightExpression, ConditionalOperator.GreaterEqual),
                leftExpression, rightExpression);
    }

    @Override
    public String toString() {
        if (leftExpression == null || rightExpression == null) {
            return "??Inexpressible " + getClass().getSimpleName() + " result due to null value??";
        }

        if (!(leftExpression.getValue() instanceof Number) || !(rightExpression.getValue() instanceof Number)) {
            return "(Improper Calculation Here...)max(" + leftExpression.toString() + "," + rightExpression.toString() + ")";
        }

        return "max(" + leftExpression.toString() + "," + rightExpression.toString() + ")";
    }
}
