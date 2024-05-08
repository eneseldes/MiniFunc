
public class Negation extends ArithmeticUnaryExpression {

    Variable value;
    
    Negation(Expression expression) {
        super(expression);
    }

    @Override
    Object getValue() {
        execute();
        return value.getValue();
    }

    @Override
    Expression execute() {
        Object inputValue = expression.getValue();
        Number resultValue = null;

        if (!(inputValue instanceof Number)) {
            return null;
        }

        if (inputValue instanceof Integer) {
            resultValue = -(int)inputValue; 
        }
        else{
            resultValue = -(double)inputValue; 
        }

        if (resultValue.doubleValue() % 1 == 0) {
            value = new IntegerVariable("resultValue", resultValue.intValue());
            return value.execute();
        } else {
            value = new DoubleVariable("resultValue", resultValue.doubleValue());
            return value.execute();
        }
    }
    
    @Override
    public String toString(){
        return "-" + expression.toString();
    }
    
}
