
public abstract class ArithmeticBinaryExpression extends ArithmeticExpression {

    Expression leftExpression;
    Expression rightExpression;
    
    public ArithmeticBinaryExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

}
