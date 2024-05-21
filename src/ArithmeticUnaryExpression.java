
public abstract class ArithmeticUnaryExpression extends ArithmeticExpression {

    Expression expression;

    ArithmeticUnaryExpression(Expression expression) {
        this.expression = expression;
    }
    
    //toString() is not overrode here since unary operations has unique representations
}
