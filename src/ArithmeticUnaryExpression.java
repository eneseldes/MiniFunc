
public abstract class ArithmeticUnaryExpression extends ArithmeticExpression {
    Expression expression;
    
    ArithmeticUnaryExpression(Expression expression){
        this.expression = expression;
    }
}
