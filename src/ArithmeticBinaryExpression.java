
public abstract class ArithmeticBinaryExpression extends ArithmeticExpression {

    Expression leftExpression;
    Expression rightExpression;

    public ArithmeticBinaryExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public String toString() {
        String operator;
        
        /*
            Check which operation is going to be occur and assign 'operator' correctly
        
            If you are going to add another binary arithmetic operation, do not
            forget to adjust switch statement below
        */
        switch(getClass().getSimpleName()){
            case "Addition":
                operator = "+";
                break;
            case "Division":
                operator = "/";
                break;
            case "Modulo":
                operator = "%";
                break;
            case "Multipication":
                operator = "*";
                break;
            case "Power":
                operator = "^";
                break;
            case "Substraction":
                operator = "/";
                break;
            default:
                operator = "Unknown Operator";
                break;
        }
        
        if (leftExpression == null || rightExpression == null)
            return "??Inexpressible " + getClass().getSimpleName() + " result due to null value??";
        
        if (!(leftExpression.getValue() instanceof Number) || !(rightExpression.getValue() instanceof Number))
            return "((Improper Calculation Here...)" + leftExpression.toString() + operator + rightExpression.toString() + ")";
        
        
        return "(" + leftExpression.toString() + operator + rightExpression.toString() + ")";
    }
    
}
