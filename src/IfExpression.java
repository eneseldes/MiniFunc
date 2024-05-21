
public class IfExpression extends Expression {
    // Condition and expressions that are going to be returned according to condition
    ConditionalExpression conditionalExpression;
    Expression ifStep;
    Expression elseStep;

    IfExpression(ConditionalExpression conditionalExpression, Expression ifStep, Expression elseStep) {
        this.conditionalExpression = conditionalExpression;
        this.ifStep = ifStep;
        this.elseStep = elseStep;
    }

    // Getting value
    @Override
    Object getValue() {
        return execute().getValue();
    }

    // Assigning and calculating
    @Override
    Expression execute() {
        // Assign the field 'value' to the expression that are going to be returned
        if ((boolean) conditionalExpression.getValue())
            return ifStep;
        else
            return elseStep;
    }

    @Override
    public String toString() {
        if (conditionalExpression == null || ifStep == null || elseStep == null)
            return "??Inexpressible " + getClass().getName() + " result due to null value??";
        
        return "if(" + conditionalExpression.toString() + ") { " + ifStep.toString() + " } else { " + elseStep.toString() + " }";
    }
}
