
public class IfExpression extends Expression {

    ConditionalExpression conditionalExpression;
    Expression ifStep;
    Expression elseStep;
    
    Expression value;
    
    IfExpression(ConditionalExpression conditionalExpression, Expression ifStep, Expression elseStep) {
        this.conditionalExpression = conditionalExpression;
        this.ifStep = ifStep;
        this.elseStep = elseStep;
    }
    
    @Override
    Object getValue() {
        execute();
        return value.getValue();
    }

    @Override
    Expression execute() {
        if ((boolean)conditionalExpression.getValue()) {
            value = ifStep;
            return ifStep;
        }
        else{
            value = elseStep;
            return elseStep;
        }
    }
    
    @Override
    public String toString(){
        return "if(" + conditionalExpression.toString() + ") { " + ifStep.toString() + " } else { " + elseStep.toString() + " }";
    }
}
