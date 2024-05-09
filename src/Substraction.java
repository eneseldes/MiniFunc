
// Check Addition.java for comments
public class Substraction extends ArithmeticBinaryExpression {

    Literal value;

    Substraction(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
    }

    @Override
    Object getValue() {
        try {
            execute();
            return value.getValue();

        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    Expression execute() {
        // Different from Addition. This class accepts only Number
        if (!(leftExpression.getValue() instanceof Number) && !(rightExpression.getValue() instanceof Number)) {
            System.out.println("Encountered improper value in " + getClass().getName() + ". Results may be inaccurate!");
            return null;
        }
        
        Object leftValue = leftExpression.getValue();
        Object rightValue = rightExpression.getValue();
        
        Number resultValue = ((Number) leftValue).doubleValue() - ((Number) rightValue).doubleValue();
        value = resultValue.doubleValue() % 1 == 0 ? 
                IntegerLiteral.create(resultValue.intValue()) : DoubleLiteral.create(resultValue.doubleValue());
        
        return value.execute();
    }

    @Override
    public String toString() {
        try{
            return "(" + leftExpression.toString() + "-" + rightExpression.toString() + ")";
        }
        catch(Exception e){
            return "**Inexpressible " + getClass().getName() + " result**";
        }
    }

}
